package utez.edu.mx.communitycommitteesystem.controller.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenDto {
    private String token;
    private String role;
    private String message;

}
