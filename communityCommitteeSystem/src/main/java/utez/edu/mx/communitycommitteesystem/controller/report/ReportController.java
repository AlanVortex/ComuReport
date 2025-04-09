package utez.edu.mx.communitycommitteesystem.controller.report;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utez.edu.mx.communitycommitteesystem.model.report.ReportBean;
import utez.edu.mx.communitycommitteesystem.security.jwt.JwtProvider;
import utez.edu.mx.communitycommitteesystem.service.report.ReportService;

import java.util.List;

@RestController
@RequestMapping("/api/report")
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;
    private final JwtProvider jwtProvider;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> registerReport(
            @ModelAttribute("dto") ReportDto dto, // Aquí mapeas los datos del DTO
            HttpServletRequest req) {
        reportService.registerReport(dto, jwtProvider.resolveClaimsJUuid(req)  );
        return ResponseEntity.ok("Report registered successfully");

    }

    @GetMapping("")
    public ResponseEntity<List<ReportSummaryDto>> getReportsByColony(HttpServletRequest req) {

        List<ReportSummaryDto> reports = reportService.getReportsByColonyUuid(jwtProvider.resolveClaimsJUuid(req) , jwtProvider.resolveClaimsJRole(req));
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
