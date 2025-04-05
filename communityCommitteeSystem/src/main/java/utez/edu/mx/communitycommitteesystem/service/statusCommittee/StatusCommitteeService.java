package utez.edu.mx.communitycommitteesystem.service.statusCommittee;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.model.statusCommittee.StatusCommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.statusCommittee.StatusCommitteerepository;

@Service
@AllArgsConstructor
public class StatusCommitteeService {

    private final StatusCommitteerepository statusCommitteerepository;

    public StatusCommitteeBean findById(Long id) {
        return statusCommitteerepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status not found"));
    }
}
