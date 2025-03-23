package utez.edu.mx.communitycommitteesystem.service.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.communitycommitteesystem.model.state.StateBean;
import utez.edu.mx.communitycommitteesystem.model.state.StateRepository;

import java.util.Optional;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;

    public Optional<StateBean> findById(Long id) {
        return stateRepository.findById(id);
    }

    public void save(StateBean state) {
        stateRepository.save(state);
    }
}
