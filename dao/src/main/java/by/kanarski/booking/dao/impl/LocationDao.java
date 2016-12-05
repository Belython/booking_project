package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.ILocationDao;
import by.kanarski.booking.entities.location.Location;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LocationDao extends ExtendedBaseDao<Location> implements ILocationDao {

    @Autowired
    public LocationDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
