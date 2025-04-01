package utez.edu.mx.communitycommitteesystem.model.municipality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;

import java.util.List;

@Repository
public interface MunicipalityRepository  extends JpaRepository<MunicipalityBean , Long> {

    // findByUuid
    MunicipalityBean findByUuid(String uuid);

    List<MunicipalityBean> findByUuidAndStateBean(String uuid, StateBean stateBean);
}
