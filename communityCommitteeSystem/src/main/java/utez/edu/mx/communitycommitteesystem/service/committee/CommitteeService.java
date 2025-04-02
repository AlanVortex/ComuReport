package utez.edu.mx.communitycommitteesystem.service.committee;

import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.controller.committee.CommitteeDto;
import utez.edu.mx.communitycommitteesystem.controller.committee.CommitteeResponseDto;
import utez.edu.mx.communitycommitteesystem.model.colony.ColonyBean;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeBean;
import utez.edu.mx.communitycommitteesystem.model.committee.CommitteeRepository;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.statusCommittee.StatusCommitteeBean;
import utez.edu.mx.communitycommitteesystem.service.colony.ColonyService;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;
import utez.edu.mx.communitycommitteesystem.service.statusCommittee.StatusCommitteeService;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class CommitteeService {

    @Autowired
    private CommitteeRepository committeeRepository;

    @Autowired
    private ColonyService colonyService;

    @Autowired
    private StatusCommitteeService statusService;

    @Autowired
    private PersonService personService;



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

    public String registerPresident(CommitteeDto committeeDto) {
        ColonyBean colony = colonyService.findById(1l);

        StatusCommitteeBean status = statusService.findById(committeeDto.getIdStatus());
        if (status == null) {
            throw new EntityNotFoundException("Error: Status no encontrado.");
        }

        PersonBean person = new PersonBean();
        person.setName(committeeDto.getName());
        person.setLastname(committeeDto.getLastname());
        person.setEmail(committeeDto.getEmail());
        person.setPhone(committeeDto.getPhone());
        person.setPassword(personService.encryptPassword(committeeDto.getPassword()));

        PersonBean savedPerson = personService.saveMun(person);

        CommitteeBean committee = new CommitteeBean();
        committee.setPersonBean(savedPerson);
        committee.setColonyBean(colony);
        committee.setStatusCommitteeBean(status);

        save(committee);

        return "Presidente registrado exitosamente";
    }

    public List<CommitteeBean> getCommitteesByColonyUuid(String colonyUuid) {
        ColonyBean colony = colonyService.findById(1L);

        return findByColony(colony);
    }

    public CommitteeResponseDto getPresidentByUuid(String uuid) {
        CommitteeBean committee = findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Comité no encontrado."));

        PersonBean person = committee.getPersonBean();
        if (person == null) {
            throw new EntityNotFoundException("Persona no encontrada.");
        }

        return new CommitteeResponseDto(
                person.getName(),
                person.getLastname(),
                person.getEmail(),
                person.getPhone()
        );
    }


    public String updatePresidentStatus(Long id, Long newStatusId) {
        CommitteeBean committee = findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Presidente no encontrado."));

        StatusCommitteeBean newStatus = new StatusCommitteeBean();
        newStatus.setId(newStatusId);
        committee.setStatusCommitteeBean(newStatus);

        save(committee);
        return "El estado del presidente ha sido actualizado correctamente.";
    }



}