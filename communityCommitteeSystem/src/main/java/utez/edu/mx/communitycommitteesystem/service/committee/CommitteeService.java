package utez.edu.mx.communitycommitteesystem.service.committee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.config.ApiResponse;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeRepository;

@Service
public class CommitteeService {

    @Autowired
    private CommitteeRepository committeeRepository;

    public void save(CommitteeBean committee) {
        committeeRepository.save(committee);
    }
}