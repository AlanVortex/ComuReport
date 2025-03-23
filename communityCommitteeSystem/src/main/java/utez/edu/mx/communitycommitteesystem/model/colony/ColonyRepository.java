package utez.edu.mx.communitycommitteesystem.model.colony;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColonyRepository extends JpaRepository<ColonyBean, Long> {
    Optional<ColonyBean> findById(Long id);
}
