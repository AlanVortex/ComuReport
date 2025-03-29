package utez.edu.mx.communitycommitteesystem.controller.report;

public class ReportStatusUpdateDto {
    private Long statusId;
    private String statusDescription;

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}
