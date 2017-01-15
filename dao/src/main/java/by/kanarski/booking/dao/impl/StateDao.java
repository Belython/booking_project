package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IStateDao;
import by.kanarski.booking.entities.State;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StateDao extends ExtendedBaseDao<State> implements IStateDao {

    @Autowired
    public StateDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
