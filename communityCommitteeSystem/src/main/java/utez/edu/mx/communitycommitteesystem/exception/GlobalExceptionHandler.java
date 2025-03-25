package utez.edu.mx.communitycommitteesystem.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error(ex);
        return ResponseEntity.ok(ErrorCatalog.IllegalArgumentException.getMessage());

    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<String> handleMissingPathVariableException(MissingPathVariableException ex) {
        logger.error(ex);
        return ResponseEntity.ok(ErrorCatalog.PATH_MISSING.getMessage());

    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<String> IndexOutOfBoundsException(IndexOutOfBoundsException ex) {
        logger.error(ex);
        return ResponseEntity.ok(ErrorCatalog.INDEX_OUT_OF_BOUNDS_EXCEPTION.getMessage());

    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        logger.error(ex);
        return ResponseEntity.ok(ErrorCatalog.MethodArgumentTypeMismatchException.getMessage());

    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> NullPointerException(NullPointerException ex) {
        logger.error(ex);
        return ResponseEntity.ok(ErrorCatalog.NullPointerException.getMessage());

    }
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> ArithmeticException(ArithmeticException ex) {
        logger.error(ex);
        return ResponseEntity.ok(ErrorCatalog.ArithmeticException.getMessage());

    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        logger.error(ex);
        return ResponseEntity.ok(ErrorCatalog.HttpRequestMethodNotSupportedException.getMessage());

    }


}