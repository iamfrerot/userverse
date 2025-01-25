package dev.frerot.userverse.configuration;

import dev.frerot.userverse.dto.ErrorResponse;
import dev.frerot.userverse.user.exceptions.UserNotFoundException;
import dev.frerot.userverse.userpreference.exceptions.UserPreferenceExists;
import dev.frerot.userverse.userpreference.exceptions.UserPreferenceNotFound;
import dev.frerot.userverse.userprofile.exceptions.UserProfileExists;
import dev.frerot.userverse.userprofile.exceptions.UserProfileNotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(false,HttpStatus.BAD_REQUEST.value(), "Invalid request",errors));

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParameters(MissingServletRequestParameterException ex){
        Map<String, String> errors=new HashMap<>();
        errors.put("missing parameter",ex.getParameterName());
        errors.put("type",ex.getParameterType());
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(false,HttpStatus.BAD_REQUEST.value(), "Invalid request",errors));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(false,HttpStatus.NOT_FOUND.value(), "User not found",ex.getMessage()));
    }

    @ExceptionHandler(UserProfileNotFound.class)
    public ResponseEntity<ErrorResponse> handleUserProfileNotFoundException(UserProfileNotFound ex){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(false,HttpStatus.NOT_FOUND.value(), "Profile not found",ex.getMessage()));
    }

    @ExceptionHandler(UserPreferenceNotFound.class)
    public ResponseEntity<ErrorResponse> handleUserPreferenceNotFoundException(UserPreferenceNotFound ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(false,HttpStatus.NOT_FOUND.value(), "User preference Not found",ex.getMessage()));
    }

    @ExceptionHandler(UserPreferenceExists.class)
    public ResponseEntity<ErrorResponse> handleUserPreferenceExists(UserPreferenceExists ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(false,HttpStatus.CONFLICT.value(), "User preference already exists",ex.getMessage()));
    }

    @ExceptionHandler(UserProfileExists.class)
    public ResponseEntity<ErrorResponse> handleUserProfileExists(UserProfileExists ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(false,HttpStatus.CONFLICT.value(), "User profile already exists",ex.getMessage()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex,HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ErrorResponse(false,HttpStatus.METHOD_NOT_ALLOWED.value(), "Method not supported on: "+request.getRequestURI(),ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(false,HttpStatus.BAD_REQUEST.value(), "Invalid request","Required request body is missing"));
    }
}
