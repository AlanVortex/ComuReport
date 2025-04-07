package utez.edu.mx.communitycommitteesystem.service.report;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.communitycommitteesystem.controller.report.ReportDto;
import utez.edu.mx.communitycommitteesystem.controller.report.ReportStatusUpdateDto;
import utez.edu.mx.communitycommitteesystem.controller.report.ReportSummaryDto;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyRepository;
import utez.edu.mx.communitycommitteesystem.model.image.ImageBean;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.report.ReportBean;
import utez.edu.mx.communitycommitteesystem.model.report.ReportRepository;
import utez.edu.mx.communitycommitteesystem.model.sms.SmsBean;
import utez.edu.mx.communitycommitteesystem.model.sms.SmsRepository;
import utez.edu.mx.communitycommitteesystem.model.status.StatusBean;
import utez.edu.mx.communitycommitteesystem.model.status.StatusRepository;
import utez.edu.mx.communitycommitteesystem.service.municipality.MunicipalityService;
import utez.edu.mx.communitycommitteesystem.service.sms.SmsService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportService {


    private final ReportRepository reportRepository;




    private final MunicipalityService municipalityService;


    private final ColonyRepository colonyRepository;

    private final StatusRepository statusRepository;


    private final SmsService smsService;

    private final SmsRepository smsRepository;

    public ReportBean registerReport(ReportDto dto, String loggedInColonyUuid) {
        Optional<ColonyBean> colony = colonyRepository.findByUuid(loggedInColonyUuid);
        if (colony == null) {
            throw new RuntimeException("Colonia no encontrada para el usuario logueado.");
        }



        MunicipalityBean municipality = municipalityService.findByUuid(dto.getMunicipalityUuid());


        ReportBean report = new ReportBean();
        report.setTitle(dto.getTitle());
        report.setDescription(dto.getDescription());
        report.setReportDate(new Date());
        report.setColonyBean(colony.get());
        report.setMunicipalityBean(municipality);

        if (report.getImageBeanList() == null) {
            report.setImageBeanList(new ArrayList<>());
        }

        for (String imageBase64 : dto.getImageBase64()) {
            ImageBean image = new ImageBean();
            image.setImage(imageBase64);
            image.setReportBean(report);
            report.getImageBeanList().add(image);
        }


        return reportRepository.save(report);
    }

    public List<ReportSummaryDto> getReportsByColonyUuid(String colonyUuid) {
        Optional<ColonyBean> colony = colonyRepository.findByUuid(colonyUuid);

        if (colony.isEmpty()) {
            throw new RuntimeException("Colonia no encontrada con UUID: " + colonyUuid);
        }

        List<ReportBean> reports = reportRepository.findByColonyBean(colony.get());


        return reports.stream().map(this::convertToDto).collect(Collectors.toList());
    }


    private ReportSummaryDto convertToDto(ReportBean report) {
        String title = (report.getTitle() != null) ? report.getTitle() : "Sin título";
        String image = (report.getImageBeanList() != null && !report.getImageBeanList().isEmpty()) ? report.getImageBeanList().get(0).getImage() : null;
        Date date = (report.getReportDate() != null) ? report.getReportDate() : null;
        String status = (report.getStatusBean() != null) ? (String) report.getStatusBean().getType() : null;

        ColonyBean colony = report.getColonyBean();
        if (colony == null) {
            return new ReportSummaryDto(title, image, date, status);
        }

        PersonBean president = colony.getPersonBean();
        if (president == null) {
            return new ReportSummaryDto(title, image, date, status);
        }

        return new ReportSummaryDto(
                title,
                image,
                date,
                president.getName(),
                president.getLastname(),
                status
        );
    }

    @Transactional
    public String updateReportStatus(String uuid, ReportStatusUpdateDto request) {
        ReportBean report = reportRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));

        StatusBean status = statusRepository.findById(request.getStatusId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        report.setStatusBean(status);
        report.setStatusDescription(request.getStatusDescription());
        reportRepository.save(report);




        String messageBody = String.format(
                "ACTUALIZACIÓN DE REPORTE\n" +
                        "Título: %s\n" +
                        "Estado: %s\n" +
                report.getTitle(),
                status.getType()
        );


        SmsBean sms = new SmsBean();
        sms.setDeliveryDate(new Date());
        sms.setReportBean(report);
        sms.setMessage(messageBody);
        smsRepository.save(sms);

        return "Reporte actualizado y SMS enviado.";
    }
}