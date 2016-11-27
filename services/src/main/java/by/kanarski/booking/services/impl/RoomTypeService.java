package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.impl.RoomTypeDao;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.services.interfaces.IRoomTypeService;

public class RoomTypeService extends ExtendedBaseService<RoomType, RoomTypeDto> implements IRoomTypeService {

    private static RoomTypeService instance;
    private static RoomTypeDao roomTypeDao = RoomTypeDao.getInstance();

    private RoomTypeService() {
    }

    public static synchronized RoomTypeService getInstance() {
        if (instance == null) {
            instance = new RoomTypeService();
        }
        return instance;
    }

}
