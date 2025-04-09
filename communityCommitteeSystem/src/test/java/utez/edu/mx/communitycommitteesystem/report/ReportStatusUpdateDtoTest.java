package utez.edu.mx.communitycommitteesystem.report;

import org.junit.jupiter.api.Test;
import utez.edu.mx.communitycommitteesystem.controller.report.ReportStatusUpdateDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReportStatusUpdateDtoTest {

    @Test
    void ReportStatusUpdateDtoTest () {
        ReportStatusUpdateDto dto = new ReportStatusUpdateDto();

        dto.setStatusDescription("Test");

        assertNotNull(dto);
        assertEquals("Test",dto.getStatusDescription());
    }
}
