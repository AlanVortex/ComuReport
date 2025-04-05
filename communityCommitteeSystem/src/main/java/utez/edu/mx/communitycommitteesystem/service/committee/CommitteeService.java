package utez.edu.mx.communitycommitteesystem.service.committee;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeRepository;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.service.colony.ColonyService;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;
import utez.edu.mx.communitycommitteesystem.service.statusCommittee.StatusCommitteeService;

import java.util.List;
import java.util.Optional;

@Service

@AllArgsConstructor
public class CommitteeService {


    private final CommitteeRepository committeeRepository;


    private final ColonyService colonyService;


    private final StatusCommitteeService statusService;


    private final PersonService personService;


    public void save(CommitteeBean committee) {
        committeeRepository.save(committee);
    }

    public CommitteeBean findByUuid(String uuid) {
        return committeeRepository.findByUuid(uuid).orElseThrow(() -> new EntityNotFoundException("Committe not found"));
    }

    public CommitteeBean findByUuid(String uuid, ColonyBean colony) {
        return committeeRepository.findByUuidAndColonyBean(uuid, colony).orElseThrow(() -> new EntityNotFoundException("Committe not found"));
    }


    public String create(CommitteeBean committeeBean, String uuid) {
        ColonyBean colony = colonyService.findByUuid(uuid);
        PersonBean savedPerson = personService.saveMun(committeeBean.getPersonBean());
        committeeBean.setPersonBean(savedPerson);
        committeeBean.setColonyBean(colony);
        save(committeeBean);

        return "Committee created successfully";
    }

    public List<CommitteeBean> findAll(String uuidColony) {

        return committeeRepository.findAllByColonyBean(colonyService.findByUuid(uuidColony));
    }

    public CommitteeBean get(String uuidColony, String uuid) {
        return findByUuid(uuid, colonyService.findByUuid(uuidColony));
    }


}