package org.example.bicyclerackapi.record.model;

import jakarta.persistence.*;
import org.example.bicyclerackapi.rack.model.Rack;

import java.time.Instant;

/**
 * Clase que representa un registro de estacionamiento de una bicicleta en un bicicletero.
 */
@Entity
@Table(name = "records")
public class Record {
    /**
     * Identificador único del registro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Matrícula (rut + año de ingreso) del estudiante que estaciona la bicicleta.
     */
    @Column(nullable = false)
    private String studentId;
    /**
     * Nombre del estudiante que estaciona la bicicleta.
     */
    @Column(nullable = false)
    private String studentName;
    /**
     * Descripción de la bicicleta estacionada.
     */
    @Column(nullable = false)
    private String bicycleDescription;
    /**
     * Fecha y hora de ingreso de la bicicleta.
     */
    @Column(nullable = false)
    private Instant checkIn;
    /**
     * Fecha y hora de salida de la bicicleta. Puede ser nulo si la bicicleta aún está estacionada.
     */
    private Instant checkOut;
    @ManyToOne(fetch = FetchType.EAGER)
    /**
     * Bicicletero donde se estaciona la bicicleta.
     */
    @JoinColumn(name = "rack_id", nullable = false)
    private Rack rack;
    /**
     * El número del gancho en el que se cuelga la bicicleta.
     */
    @Column(nullable = false)
    private Long hook;

    /**
     * Constructor por defecto.
     */
    public Record() {
    }

    /**
     * Constructor para crear un nuevo registro de estacionamiento de bicicleta.
     * El check in se establece automáticamente como la fecha y hora en la que se crea el registro y el check out se deja como nulo.
     *
     * @param studentId          la matrícula del estudiante que estaciona la bicicleta
     * @param studentName        el nombre del estudiante que estaciona la bicicleta
     * @param bicycleDescription la descripción de la bicicleta estacionada
     * @param rack               el bicicletero donde se estaciona la bicicleta
     * @param hook               el número del gancho en el que se cuelga la bicicleta
     */
    public Record(String studentId, String studentName, String bicycleDescription, Rack rack, Long hook) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.bicycleDescription = bicycleDescription;
        this.checkIn = Instant.now();
        this.rack = rack;
        this.hook = hook;
    }

    /**
     * Regresa el ID del registro.
     *
     * @return el ID del registro
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del registro.
     *
     * @param id el ID del registro
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Regresa la matrícula del estudiante que estaciona la bicicleta.
     *
     * @return la matrícula del estudiante
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Establece la matrícula del estudiante que estaciona la bicicleta.
     *
     * @param studentId la matrícula del estudiante
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Regresa el nombre del estudiante que estaciona la bicicleta.
     *
     * @return el nombre del estudiante
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Establece el nombre del estudiante que estaciona la bicicleta.
     *
     * @param studentName el nombre del estudiante
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Regresa la descripción de la bicicleta estacionada.
     *
     * @return la descripción de la bicicleta
     */
    public String getBicycleDescription() {
        return bicycleDescription;
    }

    /**
     * Establece la descripción de la bicicleta estacionada.
     *
     * @param bicycleDescription la descripción de la bicicleta
     */
    public void setBicycleDescription(String bicycleDescription) {
        this.bicycleDescription = bicycleDescription;
    }

    /**
     * Regresa la fecha y hora de ingreso de la bicicleta.
     *
     * @return la fecha y hora de ingreso
     */
    public Instant getCheckIn() {
        return checkIn;
    }

    /**
     * Establece la fecha y hora de ingreso de la bicicleta.
     *
     * @param checkIn la fecha y hora de ingreso de la bicicleta
     */
    public void setCheckIn(Instant checkIn) {
        this.checkIn = checkIn;
    }

    /**
     * Regresa la fecha y hora de salida de la bicicleta.
     *
     * @return la fecha y hora de salida
     */
    public Instant getCheckOut() {
        return checkOut;
    }

    /**
     * Establece la fecha y hora de salida de la bicicleta.
     *
     * @param checkOut la fecha y hora de salida de la bicicleta
     */
    public void setCheckOut(Instant checkOut) {
        this.checkOut = checkOut;
    }

    /**
     * Regresa el bicicletero donde se estaciona la bicicleta.
     *
     * @return el bicicletero
     */
    public Rack getRack() {
        return rack;
    }

    /**
     * Establece el bicicletero donde se estaciona la bicicleta.
     *
     * @param rack el bicicletero donde se estaciona la bicicleta
     */
    public void setRack(Rack rack) {
        this.rack = rack;
    }

    /**
     * Regresa el número del gancho en el que se cuelga la bicicleta.
     *
     * @return el número del gancho
     */
    public Long getHook() {
        return hook;
    }

    /**
     * Establece el número del gancho en el que se cuelga la bicicleta.
     *
     * @param hook el gancho en el que se cuelga la bicicleta
     */
    public void setHook(Long hook) {
        this.hook = hook;
    }
}
