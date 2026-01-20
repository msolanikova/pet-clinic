package sk.promark.petclinic.model;

import java.time.Instant;
import java.util.Map;

public record ErrorResponse(String errorCode, String message, Instant timestamp, String path,
                            Map<String, String> details) {
}
