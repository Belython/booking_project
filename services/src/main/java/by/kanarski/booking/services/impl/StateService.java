package by.kanarski.booking.services.impl;

import by.kanarski.booking.entities.State;
import by.kanarski.booking.services.interfaces.IStateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class StateService extends ExtendedBaseService<String, State> implements IStateService {


}
