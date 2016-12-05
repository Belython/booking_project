package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IBillDao;
import by.kanarski.booking.entities.Bill;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BillDao extends ExtendedBaseDao<Bill> implements IBillDao {

    @Autowired
    public BillDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
