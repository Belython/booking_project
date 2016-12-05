package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IRoomTypeDao;
import by.kanarski.booking.entities.roomType.RoomType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoomTypeDao extends ExtendedBaseDao<RoomType> implements IRoomTypeDao {

    @Autowired
    public RoomTypeDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
