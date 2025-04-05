package utez.edu.mx.communitycommitteesystem.controller.report;

import lombok.Data;

import java.util.List;
@Data
public class ReportDto {

    private String title;
    private String description;
    private List<String> imageBase64;
    private String committeeUuid;
    private String municipalityUuid;

}
