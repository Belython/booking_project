package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.interfaces.IRoomTypeDao;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.services.interfaces.IRoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class RoomTypeService extends ExtendedBaseService<RoomType, RoomTypeDto> implements IRoomTypeService {

    @Autowired
    private IRoomTypeDao roomTypeDao;

}
