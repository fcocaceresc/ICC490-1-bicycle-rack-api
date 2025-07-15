package org.example.bicyclerackapi.record.utils;

import org.example.bicyclerackapi.exception.custom.InvalidPageTokenException;
import org.example.bicyclerackapi.record.model.Record;
import org.springframework.data.domain.Slice;

import java.util.Base64;

/**
 * Clase que se encarga de decodificar, validar, y generar el token de la página siguiente para la paginación de respuestas.
 */
public class PageTokenUtils {
    /**
     * Obtiene el número de página a partir de un token de página codificado en base64.
     *
     * @param pageToken el token de página codificado en base64
     * @return el número de página obtenido del token o 0 si el token es nulo o vacío (que significa que se trata de la primera página)
     */
    public static int getPageNumberFromToken(String pageToken) {
        int pageNumber = 0;
        if (pageToken == null || pageToken.trim().isEmpty()) {
            return pageNumber;
        }
        String decodedPageToken = decodeBase64PageToken(pageToken);
        pageNumber = parseDecodedPageTokenToInt(decodedPageToken);
        validatePageNumberIsNotNegative(pageNumber);
        return pageNumber;
    }

    /**
     * Decodifica un token de página codificado en base64.
     *
     * @param encodedPageToken token de página codificado en base64
     * @return el token de página decodificado
     * @throws InvalidPageTokenException si el token no se puede decodificar
     */
    private static String decodeBase64PageToken(String encodedPageToken) {
        try {
            return new String(Base64.getDecoder().decode(encodedPageToken));
        } catch (IllegalArgumentException e) {
            throw new InvalidPageTokenException("Invalid page token");
        }
    }

    /**
     * Convierte un token de página decodificado a un número entero.
     *
     * @param decodedPageToken token de página decodificado
     * @return el número entero correspondiente al token de página
     * @throws InvalidPageTokenException si el token decodificado no es un número
     */
    private static int parseDecodedPageTokenToInt(String decodedPageToken) {
        try {
            return Integer.parseInt(decodedPageToken);
        } catch (NumberFormatException e) {
            throw new InvalidPageTokenException("Invalid page token");
        }
    }

    /**
     * Valida que el número de página no sea negativo.
     *
     * @param pageNumber número de página a validar
     * @throws InvalidPageTokenException si el número de página es negativo
     */
    private static void validatePageNumberIsNotNegative(int pageNumber) {
        if (pageNumber < 0) {
            throw new InvalidPageTokenException("Invalid page token");
        }
    }

    /**
     * Genera un token que representa la página siguiente de registros.
     *
     * @param recordSlice la página de registros actual
     * @return un token codificado en base64 que representa la página siguiente, o null si no hay más páginas
     */
    public static String generateNextPageToken(Slice<Record> recordSlice) {
        if (recordSlice.hasNext()) {
            int nextPageNumber = recordSlice.getNumber() + 1;
            return Base64.getEncoder().encodeToString(String.valueOf(nextPageNumber).getBytes());
        }
        return null;
    }
}
