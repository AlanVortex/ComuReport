package utez.edu.mx.communitycommitteesystem.model.municipality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipalityRepository  extends JpaRepository<MunicipalityBean , Long> {
}
