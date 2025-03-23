package utez.edu.mx.communitycommitteesystem.service.colony;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyRepository;

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
}
