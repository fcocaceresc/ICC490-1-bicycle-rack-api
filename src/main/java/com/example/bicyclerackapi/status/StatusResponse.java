package com.example.bicyclerackapi.status;

import java.time.LocalDateTime;

record StatusResponse(String message, LocalDateTime timestamp) {
}
