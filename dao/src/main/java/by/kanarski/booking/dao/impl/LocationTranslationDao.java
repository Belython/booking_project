package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.ILocationTranslationDao;
import by.kanarski.booking.entities.location.LocationTranslation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LocationTranslationDao extends ExtendedBaseDao<LocationTranslation> implements ILocationTranslationDao {

    @Autowired
    public LocationTranslationDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
