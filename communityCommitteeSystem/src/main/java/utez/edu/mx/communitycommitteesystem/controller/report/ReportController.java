package utez.edu.mx.communitycommitteesystem.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.communitycommitteesystem.model.report.ReportBean;
import utez.edu.mx.communitycommitteesystem.service.report.ReportService;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @PostMapping()
    public ResponseEntity<String> registerReport(@RequestBody ReportDto dto, @RequestHeader("Colony-UUID") String colonyUuid) {
        try {
            ReportBean report = reportService.registerReport(dto, colonyUuid);
            return ResponseEntity.status(HttpStatus.CREATED).body("Reporte registrado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar el reporte: " + e.getMessage());
        }
    }

    @GetMapping("/colony/{colonyUuid}")
    public ResponseEntity<List<ReportSummaryDto>> getReportsByColony(@PathVariable String colonyUuid) {
        List<ReportSummaryDto> reports = reportService.getReportsByColonyUuid(colonyUuid);
        return ResponseEntity.ok(reports);
    }

    @PutMapping("/updateStatus/{uuid}")
    public ResponseEntity<String> updateReportStatus(@PathVariable String uuid, @RequestBody ReportStatusUpdateDto request) {
        try {
            reportService.updateReportStatus(uuid, request);
            return ResponseEntity.ok("Reporte actualizado");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el estado.");
        }
    }
}
