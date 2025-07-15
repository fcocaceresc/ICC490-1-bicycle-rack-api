package org.example.bicyclerackapi.record.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.example.bicyclerackapi.exception.custom.InvalidFilterException;
import org.example.bicyclerackapi.record.model.Filter;
import org.example.bicyclerackapi.record.model.Record;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que se encarga de construir un objeto Specification a partir de un string que contiene un filtro en formato PostgreSQL.
 */
@Component
public class SpecificationBuilder {
    /**
     * Conjunto de operadores válidos para los filtros.
     */
    private static final Set<String> VALID_OPERATORS = Set.of("<", ">", "<=", ">=", "=", "<>", "!=", "IS NOT NULL", "IS NULL");
    /**
     * Patrón regex para validar el formato del filtro.
     */
    private static final Pattern FILTER_PATTERN = Pattern.compile("(.+?)\\s+(" + String.join("|", VALID_OPERATORS) + ")\\s+(.+)?");
    /**
     * Conjunto de columnas válidas para los filtros.
     */
    private static final Set<String> VALID_COLUMNS = Set.of("id", "studentId", "studentName", "bicycleDescription", "checkIn", "checkOut", "rack", "hook");

    /**
     * Construye un objeto Specification a partir de un string que representa un filtro en formato PostgreSQL.
     *
     * @param rawFilter string que representa un filtro en formato PostgreSQL
     * @return un objeto Specification que representa el filtro, o null si no se proporciona un filtro
     */
    public Specification<Record> buildSpecification(String rawFilter) {
        Filter filter = getFilter(rawFilter);
        if (filter == null) {
            return null;
        }
        return createSpecification(filter);
    }

    /**
     * Obtiene un objeto Filter a partir de un string que representa un filtro en formato PostgreSQL.
     *
     * @param rawFilter string que representa un filtro en formato PostgreSQL
     * @return un objeto Filter que representa el filtro, o null si no se proporciona un filtro
     */
    private Filter getFilter(String rawFilter) {
        if (rawFilter == null || rawFilter.trim().isEmpty()) {
            return null;
        }
        return parseFilter(rawFilter);
    }

    /**
     * Convierte un string que representa un filtro en formato PostgreSQL a un objeto Filter.
     *
     * @param rawFilter string que representa un filtro en formato PostgreSQL
     * @return un objeto Filter que representa el filtro
     * @throws InvalidFilterException si el formato del filtro es inválido
     */
    private Filter parseFilter(String rawFilter) {
        Matcher matcher = FILTER_PATTERN.matcher(rawFilter);
        if (!matcher.find()) {
            throw new InvalidFilterException("Invalid PostgreSQL syntax");
        }
        String column = matcher.group(1).trim();
        validateColumn(column);
        String operator = matcher.group(2).trim();
        String value = getValue(matcher.group(3));
        return new Filter(column, operator, value);
    }

    /**
     * Comprueba si la columna proporcionada es válida.
     *
     * @param column columna a validar
     * @throws InvalidFilterException si la columna no es válida
     */
    private void validateColumn(String column) {
        if (!VALID_COLUMNS.contains(column)) {
            throw new InvalidFilterException("Invalid column");
        }
    }

    /**
     * Obtiene el valor a comparar del filtro.
     *
     * @param value
     * @return
     */
    private String getValue(String value) {
        if (value == null) {
            return null;
        }
        return cleanValue(value);
    }

    /**
     * Limpia el valor del filtro, eliminando espacios en blanco en los extremos y eliminando comillas simples.
     *
     * @param value valor a limpiar
     * @return valor limpio
     * @throws InvalidFilterException si el valor está entre comillas dobles
     */
    private String cleanValue(String value) {
        value = value.trim();
        if (value.startsWith("\"") && value.endsWith("\"")) {
            throw new InvalidFilterException("Value must be wrapped in single quotes, not double quotes");
        }
        if (value.startsWith("'") && value.endsWith("'")) {
            return value.substring(1, value.length() - 1);
        }
        return value;
    }

    /**
     * Construye un objeto Specification a partir de un objeto Filter.
     *
     * @param filter objeto Filter que representa el filtro a aplicar
     * @return Specification que representa el filtro
     * @throws InvalidFilterException si el filtro no contiene un valor para comparar o si la columna no es válida
     */
    private Specification<Record> createSpecification(Filter filter) {
        if (filter.getOperator().equals("IS NULL")) {
            return (root, query, builder) -> builder.isNull(root.get(filter.getColumn()));
        }
        if (filter.getOperator().equals("IS NOT NULL")) {
            return (root, query, builder) -> builder.isNotNull(root.get(filter.getColumn()));
        }
        if (filter.getValue() == null) {
            throw new InvalidFilterException("Value is required");
        }
        return (root, query, builder) -> switch (filter.getColumn()) {
            case "id" -> {
                Long value = parseLongValue(filter.getValue());
                yield createLongComparison(builder, root.get(filter.getColumn()), filter.getOperator(), value);
            }
            case "studentId", "studentName", "bicycleDescription" ->
                    createStringComparison(builder, root.get(filter.getColumn()), filter.getOperator(), filter.getValue());
            case "checkIn", "checkOut" -> {
                Instant value = parseInstantValue(filter.getValue());
                yield createInstantComparison(builder, root.get(filter.getColumn()), filter.getOperator(), value);
            }
            default -> throw new InvalidFilterException("Invalid column");
        };
    }

    /**
     * Convierte un string a un Long
     *
     * @param value valor a convertir
     * @return el valor convertido a Long
     * @throws InvalidFilterException si el valor no es un número
     */
    private Long parseLongValue(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new InvalidFilterException("Invalid long value");
        }
    }

    /**
     * Convierte un string a un Instant
     *
     * @param value valor a convertir
     * @return el valor convertido a Instant
     * @throws InvalidFilterException si el valor no es una fecha y hora
     */
    private Instant parseInstantValue(String value) {
        try {
            return Instant.parse(value);
        } catch (Exception e) {
            throw new InvalidFilterException("Invalid date value");
        }
    }

    /**
     * Crea una comparación para un campo de tipo Long.
     *
     * @param builder  CriteriaBuilder para construir la consulta
     * @param path     Path<Long> que representa el campo a comparar
     * @param operator operador de comparación a utilizar ('<', '>', '<=', '>=', '=', '<>', '!=')
     * @param value    valor a comparar con el campo
     * @return un Predicate que representa la comparación
     * @throws InvalidFilterException si el operador no es válido para un campo de tipo Long
     */
    private Predicate createLongComparison(CriteriaBuilder builder, Path<Long> path, String operator, Long value) {
        return switch (operator) {
            case "<" -> builder.lessThan(path, value);
            case ">" -> builder.greaterThan(path, value);
            case "<=" -> builder.lessThanOrEqualTo(path, value);
            case ">=" -> builder.greaterThanOrEqualTo(path, value);
            case "=" -> builder.equal(path, value);
            case "<>", "!=" -> builder.notEqual(path, value);
            default -> throw new InvalidFilterException("Unsupported operator for long field");
        };
    }

    /**
     * Crea una comparación para un campo de tipo String.
     *
     * @param builder  CriteriaBuilder para construir la consulta
     * @param path     Path<String> que representa el campo a comparar
     * @param operator operador de comparación a utilizar ('=', '!=', '<>', 'IS NOT NULL', 'IS NULL')
     * @param value    valor a comparar con el campo
     * @return un Predicate que representa la comparación
     * @throws InvalidFilterException si el operador no es válido para un campo de tipo String
     */
    private Predicate createStringComparison(CriteriaBuilder builder, Path<String> path, String operator, String value) {
        return switch (operator) {
            case "=" -> builder.equal(path, value);
            case "!=", "<>" -> builder.notEqual(path, value);
            default -> throw new InvalidFilterException("Unsupported operator for string field");
        };
    }

    /**
     * Crea una comparación para un campo de tipo Instant.
     *
     * @param builder  CriteriaBuilder para construir la consulta
     * @param path     Path<Instant> que representa el campo a comparar
     * @param operator operador de comparación a utilizar ('<', '>', '<=', '>=', '=', '<>', '!=')
     * @param value    valor a comparar con el campo
     * @return un Predicate que representa la comparación
     * @throws InvalidFilterException si el operador no es válido para un campo de tipo Instant
     */
    private Predicate createInstantComparison(CriteriaBuilder builder, Path<Instant> path, String operator, Instant value) {
        return switch (operator) {
            case "<" -> builder.lessThan(path, value);
            case ">" -> builder.greaterThan(path, value);
            case "<=" -> builder.lessThanOrEqualTo(path, value);
            case ">=" -> builder.greaterThanOrEqualTo(path, value);
            case "=" -> builder.equal(path, value);
            case "<>", "!=" -> builder.notEqual(path, value);
            default -> throw new InvalidFilterException("Unsupported operator for date field");
        };
    }
}
