package org.example.bicyclerackapi.record.model;

/**
 * Representa un filtro para ser aplicado a una consulta a la base de datos.
 */
public class Filter {
    /**
     * La columna de la base de datos a la que se aplicará el filtro.
     */
    private String column;
    /**
     * EL operador de comparación a utilizar ('<', '>', '<=', '>=', '=', '<>', '!=', 'IS NOT NULL', 'IS NULL').
     */
    private String operator;
    /**
     * El valor a comparar con la columna especificada.
     */
    private String value;

    /**
     * Constructor para crear un filtro.
     *
     * @param column   la columna de la base de datos a la que se aplicará el filtro
     * @param operator el operador de comparación a utilizar
     * @param value    el valor a comparar con la columna especificada
     */
    public Filter(String column, String operator, String value) {
        this.column = column;
        this.operator = operator;
        this.value = value;
    }

    /**
     * Obtiene la columna del filtro.
     *
     * @return el nombre de la columna
     */
    public String getColumn() {
        return column;
    }

    /**
     * Establece la columna del filtro.
     *
     * @param column el nombre de la columna
     */
    public void setColumn(String column) {
        this.column = column;
    }

    /**
     * Obtiene el operador de comparación.
     *
     * @return el operador de comparación
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Establece el operador de comparación.
     *
     * @param operator el operador de comparación
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * Obtiene el valor de comparación.
     *
     * @return el valor de comparación
     */
    public String getValue() {
        return value;
    }

    /**
     * Establece el valor de comparación.
     *
     * @param value el valor de comparación
     */
    public void setValue(String value) {
        this.value = value;
    }
}
