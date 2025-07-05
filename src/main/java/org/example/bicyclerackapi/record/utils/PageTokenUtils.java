package org.example.bicyclerackapi.record.utils;

import org.example.bicyclerackapi.exception.custom.InvalidPageTokenException;
import org.example.bicyclerackapi.record.model.Record;
import org.springframework.data.domain.Slice;

import java.util.Base64;

public class PageTokenUtils {
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

    private static String decodeBase64PageToken(String encodedPageToken) {
        try {
            return new String(Base64.getDecoder().decode(encodedPageToken));
        } catch (IllegalArgumentException e) {
            throw new InvalidPageTokenException("Invalid page token");
        }
    }

    private static int parseDecodedPageTokenToInt(String decodedPageToken) {
        try {
            return Integer.parseInt(decodedPageToken);
        } catch (NumberFormatException e) {
            throw new InvalidPageTokenException("Invalid page token");
        }
    }

    private static void validatePageNumberIsNotNegative(int pageNumber) {
        if (pageNumber < 0) {
            throw new InvalidPageTokenException("Invalid page token");
        }
    }

    public static String generateNextPageToken(Slice<Record> recordSlice) {
        if (recordSlice.hasNext()) {
            int nextPageNumber = recordSlice.getNumber() + 1;
            return Base64.getEncoder().encodeToString(String.valueOf(nextPageNumber).getBytes());
        }
        return null;
    }
}
