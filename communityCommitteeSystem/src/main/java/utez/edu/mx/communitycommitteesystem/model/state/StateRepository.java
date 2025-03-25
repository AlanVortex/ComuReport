package utez.edu.mx.communitycommitteesystem.model.state;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<StateBean, Long> {

    // agregar el findbyuuid
    Optional<StateBean> findByUuid(String uuid);
}
