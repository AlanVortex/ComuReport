package utez.edu.mx.communitycommitteesystem.service.report;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import utez.edu.mx.communitycommitteesystem.controller.report.ReportDto;
import utez.edu.mx.communitycommitteesystem.controller.report.ReportStatusUpdateDto;
import utez.edu.mx.communitycommitteesystem.controller.report.ReportSummaryDto;
import utez.edu.mx.communitycommitteesystem.firebase.FirebaseInitializer;
import utez.edu.mx.communitycommitteesystem.model.area.AreaBean;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.image.ImageBean;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.report.ReportBean;
import utez.edu.mx.communitycommitteesystem.model.report.ReportRepository;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;
import utez.edu.mx.communitycommitteesystem.model.status.StatusBean;
import utez.edu.mx.communitycommitteesystem.service.area.AreaService;
import utez.edu.mx.communitycommitteesystem.service.colony.ColonyService;
import utez.edu.mx.communitycommitteesystem.service.municipality.MunicipalityService;
import utez.edu.mx.communitycommitteesystem.service.sms.SmsService;
import utez.edu.mx.communitycommitteesystem.service.state.StateService;
import utez.edu.mx.communitycommitteesystem.service.status.StatusService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportService {


    private final ReportRepository reportRepository;

    private final MunicipalityService municipalityService;


    private final ColonyService colonyService;
    private final StatusService statusService;
    private final AreaService areaService;
    private final StateService stateService;
    private final SmsService smsService;
    private FirebaseInitializer firebaseInitializer;
    private static final Logger logger = LogManager.getLogger(ReportService.class);


    public ReportBean registerReport(ReportDto dto, String loggedInColonyUuid) {
        ColonyBean colony = colonyService.findByUuid(loggedInColonyUuid);
        MunicipalityBean municipality = municipalityService.findByUuid(colony.getMunicipalityBean().getUuid());
        StateBean stateBean = stateService.findByUuid(municipality.getStateBean().getUuid());
        StatusBean statusBean = statusService.findById(1L);
        ReportBean report = new ReportBean();
        report.setTitle(dto.getTitle());
        report.setDescription(dto.getDescription());
        report.setReportDate(new Date());
        report.setColonyBean(colony);
        report.setMunicipalityBean(municipality);
        report.setStatusBean(statusBean);
        report.setStateBean(stateBean);

        List ImageBeanList = new ArrayList();
        if (!Arrays.stream(dto.getFile()).findFirst().get().isEmpty()) {
            logger.info("Tamaño  de imagens de la report" + dto.getFile().length);
            for (MultipartFile file : dto.getFile()) {
                ImageBean imageBean = new ImageBean();
                imageBean.setImage(file.getOriginalFilename());
                imageBean.setUrl(firebaseInitializer.upload(file));
                imageBean.setReportBean(report);
                logger.info(imageBean.getUrl());
                ImageBeanList.add(imageBean);

            }
            report.setImageBeanList(ImageBeanList);
        }


        return reportRepository.save(report);
    }

    public List<ReportSummaryDto> getReportsByColonyUuid(String uuid, String role) {
        logger.info(role);
        List<ReportBean> reports = new ArrayList<>();
        List<ReportSummaryDto> reportSummaryDtos = new ArrayList<>();

        switch (role) {
            case "Colony":
                ColonyBean colony = colonyService.findByUuid(uuid);
                reports = reportRepository.findByColonyBeanAndStatusBean_IdNot(colony, 3L);
                break;
            case "Municipality":
                MunicipalityBean municipality = municipalityService.findByUuid(uuid);
                reports = reportRepository.findByMunicipalityBeanAndStatusBean_Id(municipality, 1L);
                break;
            case "Area":
                AreaBean areaBean = areaService.getArea(uuid);
                reports = reportRepository.findByAreaBeanAndStatusBean_Id(areaBean, 2L);
                break;
            case "State":
                StateBean stateBean = stateService.findByUuid(uuid);
                reports = reportRepository.findByStateBean(stateBean);
                break;
        }


        reports.forEach(report -> {
            reportSummaryDtos.add(convertToDto(report));
        });


        return reportSummaryDtos;
    }


    private ReportSummaryDto convertToDto(ReportBean report) {


        return new ReportSummaryDto(
                report.getTitle(),
                report.getImageBeanList(),
                report.getReportDate(),
                report.getColonyBean().getNameColony(),
                report.getMunicipalityBean().getNameMunicipality(),
                report.getStatusBean().getType(),
                report.getUuid()
        );
    }

    @Transactional
    public String updateReportStatus(ReportStatusUpdateDto request, String uuid, String role) {
        ReportBean report = null;
        switch (role) {
            case "Municipality":
                MunicipalityBean municipality = municipalityService.findByUuid(uuid);
                StatusBean statusBean = statusService.findById(2L);
                AreaBean areaAssing = areaService.getArea(request.getUuidArea(), municipality.getUuid());
                report = reportRepository.findByMunicipalityBeanAndUuid(municipality, request.getUuid());
                report.setStatusBean(statusBean);
                report.setAreaBean(areaAssing);
                break;
            case "Area":
                StatusBean status = statusService.findById(3L);
                AreaBean areaBean = areaService.getArea(uuid);
                report = reportRepository.findByAreaBeanAndUuid(areaBean, request.getUuid());
                report.setStatusDescription(request.getStatusDescription());
                report.setStatusBean(status);
                break;
        }

        reportRepository.save(report);

/*
        StatusBean status = statusService.findById(request.getStatusId());
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
*/
        return "Reporte actualizado y SMS enviado.";
    }

    @Transactional
    public String cancelReport(ReportStatusUpdateDto request, String uuid, String role) {
        ReportBean report = null;
        StatusBean statusBean = statusService.findById(4L);

        switch (role) {
            case "Municipality":
                MunicipalityBean municipality = municipalityService.findByUuid(uuid);
                report = reportRepository.findByMunicipalityBeanAndUuid(municipality, request.getUuid());
                break;
            case "Area":
                AreaBean areaBean = areaService.getArea(uuid);
                report = reportRepository.findByAreaBeanAndUuid(areaBean, request.getUuid());
                break;
        }
        report.setStatusBean(statusBean);
        report.setStatusDescription(request.getStatusDescription());
        reportRepository.save(report);
        return "Reporte cancelado";
    }

    public ReportBean findByuuid(String uuid) {
        return reportRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Report not found"));

    }
    public List<ReportSummaryDto> findAllHistory(String uuid ,  String role) {


        List<ReportBean> reports = new ArrayList<>();
        List<ReportSummaryDto> reportSummaryDtos = new ArrayList<>();

        switch (role) {
            case "Colony":
                ColonyBean colony = colonyService.findByUuid(uuid);
                reports = reportRepository.findByColonyBean(colony);
                break;
            case "Municipality":
                MunicipalityBean municipality = municipalityService.findByUuid(uuid);
                reports = reportRepository.findByMunicipalityBean(municipality);
                break;
            case "Area":
                AreaBean areaBean = areaService.getArea(uuid);
                reports = reportRepository.findByAreaBean(areaBean);
                break;
            case "State":
                StateBean stateBean = stateService.findByUuid(uuid);
                reports = reportRepository.findByStateBean(stateBean);
                break;
        }


        reports.forEach(report -> {
            reportSummaryDtos.add(convertToDto(report));
        });


        return reportSummaryDtos;
    }
}