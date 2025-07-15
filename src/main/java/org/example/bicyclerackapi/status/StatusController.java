package org.example.bicyclerackapi.status;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

/**
 * Controlador rest que recibe una petición sobre el estado del servidor y devuelve una respuesta con información sobre este.
 */
@RestController
@RequestMapping("/status")
public class StatusController {
    /**
     * Endpoint que devuelve el estado del servidor.
     *
     * @return fecha y hora de la respuesta, un código de estado HTTP y un mensaje.
     */
    @GetMapping
    public StatusResponse getStatus() {
        return new StatusResponse(Instant.now(), 200, "Server is up and running");
    }
}
