package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.impl.StateDao;
import by.kanarski.booking.dao.interfaces.IExtendedBaseDao;
import by.kanarski.booking.entities.State;
import by.kanarski.booking.services.interfaces.IStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class StateService extends ExtendedBaseService<State, String> implements IStateService {

    @Autowired
    private StateDao stateDao;

    @Override
    protected IExtendedBaseDao<State> getDao() {
        return stateDao;
    }
}
