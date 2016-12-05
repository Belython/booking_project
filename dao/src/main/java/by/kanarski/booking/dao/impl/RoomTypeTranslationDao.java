package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IRoomTypeTranslationDao;
import by.kanarski.booking.entities.roomType.RoomTypeTranslation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoomTypeTranslationDao extends ExtendedBaseDao<RoomTypeTranslation> implements IRoomTypeTranslationDao {

    @Autowired
    public RoomTypeTranslationDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
