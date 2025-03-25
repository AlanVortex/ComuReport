package utez.edu.mx.communitycommitteesystem.service.committee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.config.ApiResponse;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommitteeService {

    @Autowired
    private CommitteeRepository committeeRepository;

    public void save(CommitteeBean committee) {
        committeeRepository.save(committee);
    }
    public List<CommitteeBean> findAll() {
        return committeeRepository.findAll();
    }

    public Optional<CommitteeBean> findById(Long id) {
        return committeeRepository.findById(id);
    }

    public CommitteeBean update(CommitteeBean committee) {
        return committeeRepository.save(committee);
    }

    public List<CommitteeBean> findByColony(ColonyBean colony) {
        return committeeRepository.findByColonyBean(colony);
    }

    public Optional<CommitteeBean> findByUuid(String uuid) {
        return Optional.ofNullable(committeeRepository.findByUuid(uuid));
    }


}