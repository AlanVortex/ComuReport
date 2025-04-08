package utez.edu.mx.communitycommitteesystem.report;

import org.junit.jupiter.api.Test;
import utez.edu.mx.communitycommitteesystem.controller.report.ReportStatusUpdateDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReportStatusUpdateDtoTest {

    @Test
    void ReportStatusUpdateDtoTest () {
        ReportStatusUpdateDto dto = new ReportStatusUpdateDto();

        dto.setStatusId(1L);
        dto.setStatusDescription("Test");

        assertNotNull(dto);
        assertEquals(1L, dto.getStatusId());
        assertEquals("Test",dto.getStatusDescription());
    }
}
