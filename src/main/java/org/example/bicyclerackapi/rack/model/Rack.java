package org.example.bicyclerackapi.rack.model;

import jakarta.persistence.*;

/**
 * Clase que representa un bicicletero.
 */
@Entity
@Table(name = "racks")
public class Rack {
    /**
     * Identificador único del bicicletero.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Número de filas del bicicletero.
     */
    @Column(nullable = false)
    private Long rows;
    /**
     * Número de columnas del bicicletero.
     */
    @Column(nullable = false)
    private Long columns;
    /**
     * Número total de ganchos en el bicicletero.
     */
    @Column(nullable = false)
    private Long totalHooks;

    /**
     * Constructor por defecto.
     */
    public Rack() {
    }

    /**
     * Constructor para crear un bicicletero.
     * @param id ID del bicicletero.
     * @param rows el número de filas del bicicletero.
     * @param columns el número de columnas del bicicletero.
     * @param totalHooks el número total de ganchos en el bicicletero.
     */
    public Rack(Long id, Long rows, Long columns, Long totalHooks) {
        this.id = id;
        this.rows = rows;
        this.columns = columns;
        this.totalHooks = totalHooks;
    }

    /**
     * Obtiene el ID del bicicletero.
     * @return el ID del bicicletero.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del bicicletero.
     * @param id el ID del bicicletero.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el número de filas del bicicletero.
     * @return el número de filas del bicicletero.
     */
    public Long getRows() {
        return rows;
    }

    /**
     * Establece el número de filas del bicicletero.
     * @param rows el número de filas del bicicletero.
     */
    public void setRows(Long rows) {
        this.rows = rows;
    }

    /**
     * Obtiene el número de columnas del bicicletero.
     * @return el número de columnas del bicicletero.
     */
    public Long getColumns() {
        return columns;
    }

    /**
     * Establece el número de columnas del bicicletero.
     * @param columns el número de columnas del bicicletero.
     */
    public void setColumns(Long columns) {
        this.columns = columns;
    }

    /**
     * Obtiene el número total de ganchos en el bicicletero.
     * @return el número total de ganchos en el bicicletero.
     */
    public Long getTotalHooks() {
        return totalHooks;
    }

    /**
     * Establece el número total de ganchos en el bicicletero.
     * @param totalHooks el número total de ganchos en el bicicletero.
     */
    public void setTotalHooks(Long totalHooks) {
        this.totalHooks = totalHooks;
    }
}
