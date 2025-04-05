package utez.edu.mx.communitycommitteesystem.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Setter
@Getter
public class ApiResponse {

    private Object data;
    private HttpStatus status;
    private boolean error;
    private String message;
}