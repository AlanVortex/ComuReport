package utez.edu.mx.communitycommitteesystem.controller.report;

import java.util.List;

public class ReportDto {

    private String title;
    private String description;
    private List<String> imageBase64;
    private String committeeUuid;
    private String municipalityUuid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(List<String> imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getCommitteeUuid() {
        return committeeUuid;
    }

    public void setCommitteeUuid(String committeeUuid) {
        this.committeeUuid = committeeUuid;
    }

    public String getMunicipalityUuid() {
        return municipalityUuid;
    }

    public void setMunicipalityUuid(String municipalityUuid) {
        this.municipalityUuid = municipalityUuid;
    }
}
