package utez.edu.mx.communitycommitteesystem.model.committee;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;

import java.util.List;

public interface CommitteeRepository extends JpaRepository<CommitteeBean, Long> {

    // crear el findbyColony
    List<CommitteeBean> findByColonyBean(ColonyBean colonyBean);

    //findbyuuid
    CommitteeBean findByUuid(String uuid);

}
