package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IFacilityDao;
import by.kanarski.booking.entities.detail.Detail;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FacilityDao extends ExtendedBaseDao<Detail> implements IFacilityDao {

    @Autowired
    public FacilityDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
