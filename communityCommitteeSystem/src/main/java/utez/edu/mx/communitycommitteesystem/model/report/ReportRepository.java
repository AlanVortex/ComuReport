package utez.edu.mx.communitycommitteesystem.model.report;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<ReportBean, Long> {

    @Query("SELECT r FROM ReportBean r WHERE r.colonyBean.uuid = :colonyUuid")
    List<ReportBean> findByColonyUuid(String colonyUuid);}
