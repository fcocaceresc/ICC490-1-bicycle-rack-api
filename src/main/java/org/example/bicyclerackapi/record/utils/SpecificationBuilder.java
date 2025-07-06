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

@Component
public class SpecificationBuilder {
    private static final Set<String> VALID_OPERATORS = Set.of("<", ">", "<=", ">=", "=", "<>", "!=", "IS NOT NULL", "IS NULL");
    private static final Pattern FILTER_PATTERN = Pattern.compile("(.+?)\\s+(" + String.join("|", VALID_OPERATORS) + ")\\s+(.+)?");
    private static final Set<String> VALID_COLUMNS = Set.of("id", "studentId", "studentName", "bicycleDescription", "checkIn", "checkOut", "rack", "hook");

    public Specification<Record> buildSpecification(String rawFilter) {
        Filter filter = getFilter(rawFilter);
        if (filter == null) {
            return null;
        }
        return createSpecification(filter);
    }

    private Filter getFilter(String rawFilter) {
        if (rawFilter == null || rawFilter.trim().isEmpty()) {
            return null;
        }
        return parseFilter(rawFilter);
    }

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

    private void validateColumn(String column) {
        if (!VALID_COLUMNS.contains(column)) {
            throw new InvalidFilterException("Invalid column");
        }
    }

    private String getValue(String value) {
        if (value == null) {
            return null;
        }
        return cleanValue(value);
    }

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

    private Long parseLongValue(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new InvalidFilterException("Invalid long value");
        }
    }

    private Instant parseInstantValue(String value) {
        try {
            return Instant.parse(value);
        } catch (Exception e) {
            throw new InvalidFilterException("Invalid date value");
        }
    }

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

    private Predicate createStringComparison(CriteriaBuilder builder, Path<String> path, String operator, String value) {
        return switch (operator) {
            case "=" -> builder.equal(path, value);
            case "!=", "<>" -> builder.notEqual(path, value);
            default -> throw new InvalidFilterException("Unsupported operator for string field");
        };
    }

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
