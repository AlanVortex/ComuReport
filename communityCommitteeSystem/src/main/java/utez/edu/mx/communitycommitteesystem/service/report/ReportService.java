package utez.edu.mx.communitycommitteesystem.service.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.controller.report.ReportDto;
import utez.edu.mx.communitycommitteesystem.controller.report.ReportSummaryDto;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyRepository;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeRepository;
import utez.edu.mx.communitycommitteesystem.model.image.ImageBean;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityRepository;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.report.ReportBean;
import utez.edu.mx.communitycommitteesystem.model.report.ReportRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private CommitteeRepository committeeRepository;

    @Autowired
    private MunicipalityRepository municipalityRepository;

    @Autowired
    private ColonyRepository colonyRepository;

    public ReportBean registerReport(ReportDto dto, String loggedInColonyUuid) {
        Optional<ColonyBean> colony = colonyRepository.findByUuid(loggedInColonyUuid);
        if (colony == null) {
            throw new RuntimeException("Colonia no encontrada para el usuario logueado.");
        }

        CommitteeBean committee = committeeRepository.findByUuid(dto.getCommitteeUuid());
        if (committee == null) {
            throw new RuntimeException("Committee no encontrado con el UUID proporcionado.");
        }

        MunicipalityBean municipality = municipalityRepository.findByUuid(dto.getMunicipalityUuid());
        if (municipality == null) {
            throw new RuntimeException("Municipio no encontrado con el UUID proporcionado.");
        }

        ReportBean report = new ReportBean();
        report.setTitle(dto.getTitle());
        report.setDescription(dto.getDescription());
        report.setReportDate(new Date());
        report.setColonyBean(colony.get());
        report.setCommitteeBean(committee);
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
        List<ReportBean> reports = reportRepository.findByColonyUuid(colonyUuid);

        return reports.stream().map(report -> new ReportSummaryDto(
                report.getTitle(),
                report.getImageBeanList().isEmpty() ? null : report.getImageBeanList().get(0).getImage(),
                report.getReportDate(),
                report.getStatusBean(),
                report.getCommitteeBean().getPersonBean().getName() + " " + report.getCommitteeBean().getPersonBean().getLastname()
        )).collect(Collectors.toList());
    }
}

