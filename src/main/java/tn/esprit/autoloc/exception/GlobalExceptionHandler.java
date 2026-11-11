
package tn.esprit.autoloc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Erreur 404 : ressource introuvable
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(
            ResourceNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "status", 404,
                        "error", "Not Found",
                        "message", ex.getMessage()
                ));
    }

    // Erreurs HTTP : 404, 400, etc.
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatus(
            ResponseStatusException ex) {

        return ResponseEntity.status(ex.getStatusCode())
                .body(Map.of(
                        "status", ex.getStatusCode().value(),
                        "error", "Erreur HTTP",
                        "message", ex.getReason() != null
                                ? ex.getReason()
                                : "Une erreur est survenue"
                ));
    }

    // Erreur 400 : données invalides dans un DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> erreurs = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                erreurs.put(error.getField(), error.getDefaultMessage())
        );

        Map<String, Object> reponse = new LinkedHashMap<>();
        reponse.put("status", 400);
        reponse.put("error", "Bad Request");
        reponse.put("message", "Erreur de validation des données");
        reponse.put("details", erreurs);

        return ResponseEntity.badRequest().body(reponse);
    }
}
