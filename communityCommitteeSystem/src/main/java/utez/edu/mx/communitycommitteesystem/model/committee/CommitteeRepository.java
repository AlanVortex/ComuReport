package utez.edu.mx.communitycommitteesystem.model.committee;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;

import java.util.List;
import java.util.Optional;

public interface CommitteeRepository extends JpaRepository<CommitteeBean, Long> {

    // crear el findbyColony
    List<CommitteeBean> findByColonyBean(ColonyBean colonyBean);

    //findbyuuid
    Optional<CommitteeBean> findByUuid(String uuid);

    Optional<CommitteeBean> findByUuidAndColonyBean(String uuid, ColonyBean colonyBean);

    List<CommitteeBean> findAllByColonyBean(ColonyBean colonyBean);
}
