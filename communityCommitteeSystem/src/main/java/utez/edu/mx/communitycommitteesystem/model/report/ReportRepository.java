package utez.edu.mx.communitycommitteesystem.model.report;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<ReportBean, Long> {


    List<ReportBean> findByColonyBean(ColonyBean colony);

}

