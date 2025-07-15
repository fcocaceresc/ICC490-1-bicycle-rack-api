package org.example.bicyclerackapi.record.repository;

import org.example.bicyclerackapi.record.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Interfaz que se encarga de definir los métodos de acceso a los datos de la tabla Records.
 */
public interface RecordRepository extends JpaRepository<Record, Long>, JpaSpecificationExecutor<Record> {
    /**
     * Comprueba si existe un registro con la ID de un estudiante y fecha y hora de salida nula (el estudiante ya tiene una bicicleta estacionada).
     *
     * @param studentId el ID del estudiante que se quiere comprobar.
     * @return true si existe un registro con la ID del estudiante y fecha y hora de salida nula, false en caso contrario.
     */
    boolean existsByStudentIdAndCheckOutIsNull(String studentId);

    /**
     * Comprueba si existe un registro en un bicicletero específico con un gancho específico y fecha y hora de salida nula (el gancho está ocupado).
     *
     * @param rackId el ID del bicicletero donde se quiere comprobar el gancho.
     * @param hook   el número del gancho que se quiere comprobar si está ocupado.
     * @return true si existe un registro en el bicicletero con el gancho especificado y fecha y hora de salida nula, false en caso contrario.
     */
    boolean existsByRackIdAndHookAndCheckOutIsNull(Long rackId, Long hook);
}
