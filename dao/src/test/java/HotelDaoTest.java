import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.dao.interfaces.IHotelDao;
import by.kanarski.booking.dao.interfaces.IRoomTypeDao;
import by.kanarski.booking.dao.interfaces.IUserDao;
import by.kanarski.booking.dto.SearchOrder;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.exceptions.DaoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/dao-test-config.xml")
@Transactional(propagation = Propagation.SUPPORTS)
public class HotelDaoTest extends Assert {

    @Autowired
    private IHotelDao hotelDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRoomTypeDao roomTypeDao;

    private SearchOrder searchOrder;
    private List<Hotel> expectedHotelList;
    private Long expectedHotelsCount;

    @Before
    public void setUp() throws Exception {
        searchOrder = createOrder();
        expectedHotelList = getExpectedHotelList();
        expectedHotelsCount = 3L;
    }

    @After
    public void tearDown() throws Exception {
        searchOrder = null;
    }

    @Test
    public void getListByOrder() throws Exception {
        List<Hotel> actual = hotelDao.getListByOrder(searchOrder, 1, 5);
        assertArrayEquals(actual.toArray(), expectedHotelList.toArray());
    }

    @Test
    public void getHotelsCount() throws Exception {
        Long actual = hotelDao.getHotelsCount(searchOrder);
        assertEquals(actual, expectedHotelsCount);
    }

    private SearchOrder createOrder() throws DaoException {
        User user = userDao.getById(1L);
        Hotel hotel = hotelDao.getById(1L);
        Hotel orderHotel = new Hotel(null, "any", hotel.getLocation(), null, null, FieldValue.STATUS_ACTIVE);
        RoomType roomType = roomTypeDao.getById(1L);
        RoomType orderRoomType = new RoomType(null, null, null, roomType.getPricePerNight(), roomType.getFacilitySet(),
                null, null, FieldValue.STATUS_ACTIVE);
        Integer totalPersons = 5;
        Integer totalRooms = 2;
        Long checkInDate = 1000000000000000L;
        Long checkOutDate = 1500000000000000L;
        Boolean sortPriceAsc = true;
        return new SearchOrder(user, orderHotel, orderRoomType, totalPersons, totalRooms, checkInDate, checkOutDate, sortPriceAsc);
    }

    private List<Hotel> getExpectedHotelList() throws DaoException {
        Hotel hotel2 = hotelDao.getById(2L);
        Hotel hotel3 = hotelDao.getById(3L);
        List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(hotel2);
        hotelList.add(hotel3);
        return hotelList;
    }



}