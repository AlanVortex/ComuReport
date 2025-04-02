package utez.edu.mx.communitycommitteesystem.model.statusCommittee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusCommitteerepository extends JpaRepository<StatusCommitteeBean, Long> {
    Optional<StatusCommitteeBean> findById(Long id);

}
