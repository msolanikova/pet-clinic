package sk.promark.petclinic.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import sk.promark.petclinic.model.ErrorResponse;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class PetClinicExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(PetClinicExceptionHandler.class);

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainExceptions(DomainException ex, HttpServletRequest request) {
        log.warn("Domain exception raised for request {} {}: {}", request.getMethod(), request.getRequestURI(),
                ex.getMessage(), ex);

        DomainError error = ex.getError();
        ErrorResponse body = new ErrorResponse(error.code(), ex.getMessage(), Instant.now(), request.getRequestURI(),
                null);

        return ResponseEntity.status(error.status()).body(body);
    }

    @ExceptionHandler({HandlerMethodValidationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestExceptions(Exception ex, HttpServletRequest request) {
        log.warn("Validation failed for request {} {}: {}", request.getMethod(), request.getRequestURI(),
                ex.getMessage(), ex);

        Map<String, String> details = new LinkedHashMap<>();
        if (ex instanceof MethodArgumentNotValidException manve) {
            details = getDetailsForMethodArgumentNotValidException(manve);
        } else if (ex instanceof HandlerMethodValidationException hmve) {
            details = getDetailsForHandlerMethodValidationException(hmve);
        }

        ErrorResponse body = new ErrorResponse("VALIDATION_ERROR", ex.getMessage(), Instant.now(),
                request.getRequestURI(), details);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        if (ex instanceof NoResourceFoundException && "favicon.ico".equals(((NoResourceFoundException) ex).getResourcePath())) {
            return ResponseEntity.notFound().build();
        }
        log.warn("Exception for request {} {}: {}", request.getMethod(), request.getRequestURI(), ex.getMessage(), ex);

        Map<String, String> details = new LinkedHashMap<>();
        ErrorResponse body = new ErrorResponse("VALIDATION_ERROR", ex.getMessage(), Instant.now(),
                request.getRequestURI(), details);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    private Map<String, String> getDetailsForHandlerMethodValidationException(HandlerMethodValidationException ex) {
        Map<String, String> details = new LinkedHashMap<>();
        ex.getValueResults().forEach(result -> {
            String param = result.getMethodParameter().getParameterName(); // may be null if not compiled with
            // -parameters
            if (param == null) {
                param = result.getMethodParameter().getParameter().getName(); // fallback (often arg0)
            }

            final String p = param;
            result.getResolvableErrors().forEach(err -> details.put(p, err.getDefaultMessage()));
        });

        return details;
    }

    private Map<String, String> getDetailsForMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> details = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> details.put(err.getField(), err.getDefaultMessage()));

        return details;
    }
}
