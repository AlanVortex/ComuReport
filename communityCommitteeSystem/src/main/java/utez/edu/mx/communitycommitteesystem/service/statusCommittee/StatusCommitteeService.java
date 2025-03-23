package utez.edu.mx.communitycommitteesystem.service.statusCommittee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.model.status.StatusBean;
import utez.edu.mx.communitycommitteesystem.model.status.StatusRepository;
import utez.edu.mx.communitycommitteesystem.model.statusCommittee.StatusCommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.statusCommittee.StatusCommitteerepository;

@Service
public class StatusCommitteeService {
    @Autowired
    private StatusCommitteerepository statusCommitteerepository;

    public StatusCommitteeBean findById(Long id) {
        return statusCommitteerepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status not found"));
    }
}
