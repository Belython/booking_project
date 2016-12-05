package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IHotelTranslationDao;
import by.kanarski.booking.entities.hotel.HotelTranslation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HotelTranslationDao extends ExtendedBaseDao<HotelTranslation> implements IHotelTranslationDao {

    @Autowired
    public HotelTranslationDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
