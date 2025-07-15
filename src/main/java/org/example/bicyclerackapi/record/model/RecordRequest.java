package org.example.bicyclerackapi.record.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Clase que representa una solicitud para crear o actualizar un registro. Define los campos necesarios y sus restricciones.
 */
public class RecordRequest {
    /**
     * Matrícula (rut + año de ingreso) del estudiante que estaciona la bicicleta. No puede ser nulo o un string vacío.
     */
    @NotBlank(message = "studentId is a required field and can't be null or empty")
    @Size(max = 255, message = "studentId must be less than or equal to 255 characters")
    private String studentId;
    /**
     * Nombre del estudiante que estaciona la bicicleta. No puede ser nulo o un string vacío.
     */
    @NotBlank(message = "studentName is a required field and can't be null or empty")
    @Size(max = 255, message = "studentName must be less than or equal to 255 characters")
    private String studentName;
    /**
     * Descripción de la bicicleta.
     */
    @NotBlank(message = "bicycleDescription is a required field and can't be null or empty")
    @Size(max = 255, message = "bicycleDescription must be less than or equal to 255 characters")
    private String bicycleDescription;
    /**
     * ID del bicicletero donde se estaciona la bicicleta. No puede ser nulo.
     */
    @NotNull(message = "rackId is a required field and can't be null")
    private Long rackId;
    /**
     * Gancho en el que se cuelga la bicicleta. No puede ser nulo.
     */
    @NotNull(message = "hook is a required field and can't be null")
    private Long hook;

    /**
     * Constructor de la clase.
     *
     * @param studentId          la matrícula (rut + año de ingreso) del estudiante que estaciona la bicicleta
     * @param studentName        el nombre del estudiante que estaciona la bicicleta
     * @param bicycleDescription la descripción de la bicicleta
     * @param rackId             el ID del bicicletero donde se estaciona la bicicleta
     * @param hook               el número del gancho en el que se cuelga la bicicleta
     */
    public RecordRequest(String studentId, String studentName, String bicycleDescription, Long rackId, Long hook) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.bicycleDescription = bicycleDescription;
        this.rackId = rackId;
        this.hook = hook;
    }

    /**
     * Obtiene la matrícula del estudiante que estaciona la bicicleta.
     *
     * @return la matrícula del estudiante
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Establece la matrícula del estudiante que estaciona la bicicleta.
     *
     * @param studentId matrícula del estudiante
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Obtiene el nombre del estudiante que estaciona la bicicleta.
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
     * Obtiene la descripción de la bicicleta.
     *
     * @return la descripción de la bicicleta
     */
    public String getBicycleDescription() {
        return bicycleDescription;
    }

    /**
     * Establece la descripción de la bicicleta.
     *
     * @param bicycleDescription la descripción de la bicicleta
     */
    public void setBicycleDescription(String bicycleDescription) {
        this.bicycleDescription = bicycleDescription;
    }

    /**
     * Obtiene el ID del bicicletero donde se estaciona la bicicleta.
     *
     * @return el ID del bicicletero
     */
    public Long getRackId() {
        return rackId;
    }

    /**
     * Establece el ID del bicicletero donde se estaciona la bicicleta.
     *
     * @param rackId ID del bicicletero
     */
    public void setRackId(Long rackId) {
        this.rackId = rackId;
    }

    /**
     * Obtiene el número del gancho en el que se cuelga la bicicleta.
     *
     * @return el número del gancho
     */
    public Long getHook() {
        return hook;
    }

    /**
     * Establece el número del gancho en el que se cuelga la bicicleta.
     *
     * @param hook el número del gancho
     */
    public void setHook(Long hook) {
        this.hook = hook;
    }
}
