package utez.edu.mx.communitycommitteesystem.controller.status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStatusDto {
    private Long idStatus;

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }
}
