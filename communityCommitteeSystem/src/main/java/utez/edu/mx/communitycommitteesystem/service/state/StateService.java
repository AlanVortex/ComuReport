package utez.edu.mx.communitycommitteesystem.service.state;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.controller.person.PersonUpdateContact;
import utez.edu.mx.communitycommitteesystem.model.person.PersonBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateRepository;
import utez.edu.mx.communitycommitteesystem.service.person.PersonService;
import utez.edu.mx.communitycommitteesystem.service.report.ReportService;

@Service
public class StateService {

    private final StateRepository stateRepository;
    private final PersonService personService;
    private ReportService reportService;
    public StateService(StateRepository stateRepository,
                        PersonService personService,
                        @Lazy ReportService reportService) {
        this.stateRepository = stateRepository;
        this.personService = personService;
        this.reportService = reportService;
    }


    public String registerStateWithAdmin(StateBean stateBean) {
        PersonBean personBean =  personService.save(stateBean.getPersonBean());
        stateBean.setPersonBean(personBean);
        stateRepository.save(stateBean);
        return "Estado y administrador registrados correctamente";
    }

    public StateBean findByUuid(String stateUuid) {
        // Buscar el estado por su UUID
       return stateRepository.findByUuid(stateUuid).orElseThrow(() -> new EntityNotFoundException("Not fount state"));
    }

    public StateBean update(String uuid, PersonUpdateContact personUpdateContact ) {
        StateBean state = findByUuid(uuid);
        state.getPersonBean().setPhone(personUpdateContact.getPhone());
        state.getPersonBean().setEmail(personUpdateContact.getEmail());
        return stateRepository.save(state);
    }

    public String delete(String uuid) {
        StateBean state = findByUuid(uuid);
        if (!reportService.getReportsByColonyUuid(state.getUuid() , state.getPersonBean().getRole()).isEmpty()){
            state.setStatus(false);
            state.getPersonBean().setStatus(false);
            stateRepository.save(state);
            return "State disabled successfully";
        }
        personService.delete(findByUuid(uuid).getPersonBean());
        return "State delete succeessfully";
    }

    public String transfer(String uuid,StateBean stateBean) {
        StateBean state = findByUuid(uuid);
        PersonBean personBean =  personService.save(stateBean.getPersonBean());
        state.setPersonBean(personBean);
        stateRepository.save(state);
        return "Estado y administrador registrados correctamente";
    }

}
