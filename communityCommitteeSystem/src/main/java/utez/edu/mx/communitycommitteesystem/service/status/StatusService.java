package utez.edu.mx.communitycommitteesystem.service.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.model.status.StatusBean;
import utez.edu.mx.communitycommitteesystem.model.status.StatusRepository;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;

    public StatusBean findById(Long id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status not found"));
    }
}
