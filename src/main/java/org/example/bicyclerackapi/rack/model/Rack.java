package org.example.bicyclerackapi.rack.model;

import jakarta.persistence.*;

@Entity
@Table(name = "racks")
public class Rack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Long rows;
    @Column(nullable = false)
    private Long columns;
    @Column(nullable = false)
    private Long totalHooks;

    public Rack() {
    }

    public Rack(Long id, Long rows, Long columns, Long totalHooks) {
        this.id = id;
        this.rows = rows;
        this.columns = columns;
        this.totalHooks = totalHooks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRows() {
        return rows;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }

    public Long getColumns() {
        return columns;
    }

    public void setColumns(Long columns) {
        this.columns = columns;
    }

    public Long getTotalHooks() {
        return totalHooks;
    }

    public void setTotalHooks(Long totalHooks) {
        this.totalHooks = totalHooks;
    }
}
