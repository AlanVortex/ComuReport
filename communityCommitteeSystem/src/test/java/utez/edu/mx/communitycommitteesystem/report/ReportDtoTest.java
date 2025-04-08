package utez.edu.mx.communitycommitteesystem.report;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import utez.edu.mx.communitycommitteesystem.controller.report.ReportDto;
import utez.edu.mx.communitycommitteesystem.service.report.ReportService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReportDtoTest
{
    @Autowired
    private ReportService reportService;
    @Test
    void send(){
        ReportDto reportDto = new ReportDto();
        reportDto.setTitle("Test");
        reportDto.setDescription("Test description");


        reportDto.getDescription();
        reportDto.getTitle();

        assertEquals("Test", reportDto.getTitle());
        assertEquals("Test description", reportDto.getDescription());


    }
}
