package utez.edu.mx.communitycommitteesystem.service.colony;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyRepository;
import utez.edu.mx.communitycommitteesystem.model.municipality.MunicipalityBean;

import java.util.Optional;

@Service
public class ColonyService {
    @Autowired
    private ColonyRepository colonyRepository;

    public ColonyBean findById(Long id) {
        return colonyRepository.findById(id).orElseThrow(() -> new RuntimeException("Colony not found"));
    }

    public void save(ColonyBean colony) {
        colonyRepository.save(colony);
    }

    public Optional<ColonyBean> findByUuid(String uuid) {
        return colonyRepository.findByUuid(uuid);
    }
}