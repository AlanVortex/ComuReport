package utez.edu.mx.communitycommitteesystem.service.municipality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityRepository;

import java.util.Optional;

@Service
public class MunicipalityService {
    @Autowired
    private MunicipalityRepository municipalityRepository;

    public Optional<MunicipalityBean> findById(Long id) {
        return municipalityRepository.findById(id);
    }

    public MunicipalityBean save(MunicipalityBean municipality) {
        return municipalityRepository.save(municipality);
    }

    public Optional<MunicipalityBean> findByUuid(String uuid) {
        return Optional.ofNullable(municipalityRepository.findByUuid(uuid));
    }

}
