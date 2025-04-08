package utez.edu.mx.communitycommitteesystem.controller.report;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Data
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



}
