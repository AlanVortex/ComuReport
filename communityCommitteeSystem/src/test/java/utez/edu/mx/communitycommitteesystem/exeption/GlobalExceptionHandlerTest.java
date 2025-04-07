package utez.edu.mx.communitycommitteesystem.exeption;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import utez.edu.mx.communitycommitteesystem.exception.GlobalExceptionHandler;
import utez.edu.mx.communitycommitteesystem.exception.ErrorCatalog;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleDataIntegrityViolationExceptionTest() {
        DataIntegrityViolationException ex = new DataIntegrityViolationException("Duplicate entry");
        ResponseEntity<String> response = exceptionHandler.handleDataIntegrityViolationException(ex);
        assertEquals(400, response.getStatusCode().value());
        assertEquals(ErrorCatalog.DATA_INTEGRITY_VIOLATION_EXCEPTION.getMessage(), response.getBody());
    }

    @Test
    void handleEntityNotFoundExceptionTest() {
        EntityNotFoundException ex = new EntityNotFoundException("Not found");
        ResponseEntity<String> response = exceptionHandler.handleEntityNotFoundException(ex);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(ErrorCatalog.ENTITY_NOT_FOUND_EXCEPTION.getMessage(), response.getBody());
    }

    @Test
    void handleUsernameNotFoundExceptionTest() {
        UsernameNotFoundException ex = new UsernameNotFoundException("User not found");
        ResponseEntity<String> response = exceptionHandler.handleUsernameNotFoundException(ex);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(ErrorCatalog.USERNAME_NOT_FOUND_EXCEPTION.getMessage(), response.getBody());
    }

    @Test
    void handleIllegalArgumentExceptionTest() {
        IllegalArgumentException ex = new IllegalArgumentException("Invalid argument");
        ResponseEntity<String> response = exceptionHandler.handleIllegalArgumentException(ex);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(ErrorCatalog.ILLEGAL_ARGUMENT_EXCEPTION.getMessage(), response.getBody());
    }

    @Test
    void handleMissingPathVariableExceptionTest() {
        MissingPathVariableException ex = new MissingPathVariableException("id", null);
        ResponseEntity<String> response = exceptionHandler.handleMissingPathVariableException(ex);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(ErrorCatalog.PATH_MISSING.getMessage(), response.getBody());
    }

    @Test
    void handleIndexOutOfBoundsExceptionTest() {
        IndexOutOfBoundsException ex = new IndexOutOfBoundsException("Index error");
        ResponseEntity<String> response = exceptionHandler.handleIndexOutOfBoundsException(ex);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(ErrorCatalog.INDEX_OUT_OF_BOUNDS_EXCEPTION.getMessage(), response.getBody());
    }

    @Test
    void handleMethodArgumentTypeMismatchExceptionTest() {
        MethodArgumentTypeMismatchException ex =new MethodArgumentTypeMismatchException("Errro",null,null,null,null);
        ResponseEntity<String> response = exceptionHandler.handleMethodArgumentTypeMismatchException(ex);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(ErrorCatalog.METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION.getMessage(), response.getBody());
    }

    @Test
    void handleNullPointerExceptionTest() {
        NullPointerException ex = new NullPointerException("Null ref");
        ResponseEntity<String> response = exceptionHandler.handleNullPointerException(ex);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(ErrorCatalog.NULL_POINTER_EXCEPTION.getMessage(), response.getBody());
    }

    @Test
    void handleArithmeticExceptionTest() {
        ArithmeticException ex = new ArithmeticException("Divide by zero");
        ResponseEntity<String> response = exceptionHandler.handleArithmeticException(ex);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(ErrorCatalog.ARITHMETIC_EXCEPTION.getMessage(), response.getBody());
    }

    @Test
    void handleHttpRequestMethodNotSupportedExceptionTest() {
        HttpRequestMethodNotSupportedException ex = new HttpRequestMethodNotSupportedException("POST");
        ResponseEntity<String> response = exceptionHandler.handleHttpRequestMethodNotSupportedException(ex);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(ErrorCatalog.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION.getMessage(), response.getBody());
    }
}
