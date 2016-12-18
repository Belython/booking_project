package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IFacilityDao;
import by.kanarski.booking.entities.facility.Facility;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FacilityDao extends ExtendedBaseDao<Facility> implements IFacilityDao {

    @Autowired
    public FacilityDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
