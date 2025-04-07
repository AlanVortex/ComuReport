package utez.edu.mx.communitycommitteesystem.controller.report;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ReportDto {

    private String title;
    private String description;
    private List<String> imageBase64;
    private String committeeUuid;
    private String municipalityUuid;

}
