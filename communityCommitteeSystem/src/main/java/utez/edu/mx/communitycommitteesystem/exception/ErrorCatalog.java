package utez.edu.mx.communitycommitteesystem.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public enum ErrorCatalog {
    UNAUTHORIZED_ACCESS(1005, "Acceso no autorizado"),
    RESOURCE_NOT_FOUND(1005, "EndPoint no encontrado"),
    PATH_MISSING(1006, "Falta de parametros"),
    INDEX_OUT_OF_BOUNDS_EXCEPTION(1007,"Error en la busqueda de listas"),
    MethodArgumentTypeMismatchException(1010 ,"Error en envio de parametros"),
    NullPointerException(1011,"Valores nulos"),
    ArithmeticException(1012,"Error de aritmetica"),
    HttpRequestMethodNotSupportedException(1013,"Metodo no soportado"),
    INTERNAL_SERVER_ERROR(5000, "Error interno del servidor"),
    IllegalArgumentException(9000,"Argumento invalido"),
    UsernameNotFoundException(9001,"Usuario invalido"),
    EntityNotFoundException(9002,"Entidad no encontrada"),;
    private final int code;
    private final String message;

    ErrorCatalog(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}