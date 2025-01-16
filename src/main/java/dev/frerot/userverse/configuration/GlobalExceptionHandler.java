package dev.frerot.userverse.configuration;

import dev.frerot.userverse.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, HttpServletRequest request) {
        System.err.println("------- Error------");
        System.err.println(ex.getMessage());
        System.err.println(ex.getClass());
        System.err.println("End-Point: "+request.getRequestURI());
        System.err.println("------ End Error ------");

        Map<String,Object> error=new HashMap<>();

        error.put("error",ex.getLocalizedMessage());
        error.put("timestamp",System.currentTimeMillis());
        ErrorResponse responseBody = new ErrorResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error", error);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    };

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistingEmailI(Exception ex){
        // Extracting relevant details from the exception
        String message = "A record with the same key already exists.";
        String duplicateValue = null;

        Pattern pattern = Pattern.compile("dup key: \\{ (.+?) }");
        Matcher matcher = pattern.matcher(ex.getMessage());
        if (matcher.find()) {
            duplicateValue = matcher.group(1); // Extracting the duplicate field and value
            message = "A user with this " + duplicateValue + " already exists.";
        }


        // Creating a response body
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());

        // Returning a structured response

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(false,HttpStatus.BAD_REQUEST.value(),message,body));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex,HttpServletRequest request){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(false,HttpStatus.BAD_REQUEST.value(), "Invalid request",errors));

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParameters(MissingServletRequestParameterException ex){
        Map<String, String> errors=new HashMap<>();
        errors.put("missing parameter",ex.getParameterName());
        errors.put("type",ex.getParameterType());

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(false,HttpStatus.BAD_REQUEST.value(), "invalid request",errors));

    }

}
