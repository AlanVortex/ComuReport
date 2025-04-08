package utez.edu.mx.communitycommitteesystem.controller.report;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter
@Setter
public class ReportDto {

    private String title;
    private String description;
    private MultipartFile[] file;

}
