package org.example.bicyclerackapi.record.utils;

import org.example.bicyclerackapi.exception.custom.HookIsOccupiedException;
import org.example.bicyclerackapi.exception.custom.InvalidHookException;
import org.example.bicyclerackapi.exception.custom.RecordAlreadyCheckedOutException;
import org.example.bicyclerackapi.exception.custom.StudentHasANotCheckedOutRecordException;
import org.example.bicyclerackapi.rack.model.Rack;
import org.example.bicyclerackapi.record.model.Record;
import org.springframework.stereotype.Component;

/**
 * Clase para validar las operaciones relacionadas con los registros.
 */
@Component
public class RecordValidator {
    /**
     * Comprueba que un gancho existe en el bicicletero.
     *
     * @param rack el bicicletero donde se encuentra el gancho
     * @param hook el gancho que se quiere comprobar
     * @throws InvalidHookException si el gancho no existe en el bicicletero
     */
    public void validateHookExists(Rack rack, Long hook) {
        if (hook <= 0 || hook > rack.getTotalHooks()) {
            throw new InvalidHookException("The hook does not exist in the rack");
        }
    }

    /**
     * Comprueba que un gancho no está ocupado.
     *
     * @param hookIsOccupied indica si el gancho está ocupado
     * @throws HookIsOccupiedException si el gancho ya está ocupado
     */
    public void validateHookIsNotOccupied(boolean hookIsOccupied) {
        if (hookIsOccupied) {
            throw new HookIsOccupiedException("The hook is already occupied");
        }
    }

    /**
     * Comprueba que un estudiante no tenga ya una bicicleta estacionada.
     *
     * @param studentHasCheckedOutRecord indica si el estudiante ya tiene una bicicleta estacionada
     * @throws StudentHasANotCheckedOutRecordException si el estudiante ya tiene una bicicleta estacionada
     */
    public void validateStudentHasANotCheckedOutRecord(boolean studentHasCheckedOutRecord) {
        if (studentHasCheckedOutRecord) {
            throw new StudentHasANotCheckedOutRecordException("The student has a not checked out record");
        }
    }

    /**
     * Comprueba que un registro no esté ya marcado como retirado.
     *
     * @param existingRecord el registro existente que se quiere comprobar
     * @throws RecordAlreadyCheckedOutException si el registro ya está marcado como retirado
     */
    public void validateRecordIsNotAlreadyCheckedOut(Record existingRecord) {
        if (existingRecord.getCheckOut() != null) {
            throw new RecordAlreadyCheckedOutException("The record is already checked out");
        }
    }
}
