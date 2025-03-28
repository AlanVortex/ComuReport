package utez.edu.mx.communitycommitteesystem.controller.report;

import java.time.LocalDate;
import java.util.Date;

public class ReportSummaryDto {
    private String title;
    private String image;
    private LocalDate date;

    private String status;
    private String presidentName;
    private String presidentLastname;

    public ReportSummaryDto(String title, String s, Date reportDate, String status) {
    }

    public ReportSummaryDto(String title, String image, Date date, String name, String lastname, String status) {
        this.title = title;
        this.image = image;
        this.date = LocalDate.now();
        this.presidentName = name;
        this.presidentLastname = lastname;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public String getPresidentName() {
        return presidentName;
    }

    public void setPresidentName(String presidentName) {
        this.presidentName = presidentName;
    }

    public String getPresidentLastname() {
        return presidentLastname;
    }

    public void setPresidentLastname(String presidentLastname) {
        this.presidentLastname = presidentLastname;
    }

    public String getStatus() {
        return status;
    }
}
