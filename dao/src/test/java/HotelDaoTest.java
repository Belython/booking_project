import by.kanarski.booking.dao.impl.HotelDao;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.exceptions.DaoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pre.util.TestEntityBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class HotelDaoTest extends Assert {

//    private HotelDao hotelDao = HotelDao.getInstance();
//
//    private Hotel expectedHotel;
//    private List<Hotel> expectedHotelList;
//    private Hotel newHotel;
//    private List<Hotel> updatedHotelList;
//    private Hotel updatedHotel;
//    private List<Hotel> expectedHotelListByCountry;
//    private List<Hotel> expectedHotelListByCity;
//
//    @Before
//    public void setUp() {
//        expectedHotel = TestEntityBuilder.EXPECTED_HOTEL;
//        expectedHotelList = TestEntityBuilder.EXPECTED_HOTEL_LIST;
//        newHotel = TestEntityBuilder.NEW_HOTEL;
//        updatedHotelList = TestEntityBuilder.UPDATED_HOTEL_LIST;
//        updatedHotel = TestEntityBuilder.UPDATED_HOTEL;
//        expectedHotelListByCountry = TestEntityBuilder.EXPECTED_HOTEL_LIST_BY_COUNTRY;
//        expectedHotelListByCity = TestEntityBuilder.EXPECTED_HOTEL_LIST_BY_CITY;
//    }
//
//    @After
//    public void tearDown() {
//        expectedHotelList = null;
//        expectedHotel = null;
//        newHotel = null;
//        updatedHotelList = null;
//        updatedHotel = null;
//        expectedHotelListByCountry = null;
//        expectedHotelListByCity = null;
//    }
//
//    @Test
//    public void testGetInstance() throws Exception {
//        HotelDao instance1 = HotelDao.getInstance();
//        HotelDao instance2 = HotelDao.getInstance();
//        assertEquals(instance1.hashCode(), instance2.hashCode());
//    }
//
//    @Test
//    public void testAdd() throws Exception {
//        Hotel expected = hotelDao.add(newHotel);
//        Hotel actual = hotelDao.getById(expected.getHotelId());
//        hotelDao.delete(expected);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testGetById() throws Exception {
//        try {
//            Hotel actual = hotelDao.getById(expectedHotel.getHotelId());
//            assertEquals(expectedHotel, actual);
//        } catch (DaoException e) {
//            fail();
//        }
//    }
//
//    @Test
//    public void testGetAll() throws Exception {
//        List<Hotel> actual = hotelDao.getAll();
//        assertEquals(expectedHotelList, actual);
//    }
//
//    @Test
//    public void testUpdate() throws Exception {
//        hotelDao.update(updatedHotel);
//        Hotel actual = hotelDao.getById(updatedHotel.getHotelId());
//        assertEquals(updatedHotel, actual);
//        hotelDao.update(expectedHotel);
//    }
//
//    @Test(expected = DaoException.class)
//    public void testDelete() throws Exception {
//        newHotel = hotelDao.add(newHotel);
//        hotelDao.delete(newHotel);
//        Hotel deletedHotel = hotelDao.getById(newHotel.getHotelId());
//        assertNull(deletedHotel);
//    }
//
//    @Test
//    public void testUpdateList() throws Exception {
//        hotelDao.updateList(updatedHotelList);
//        List<Hotel> actualHotelList = new ArrayList<>();
//        for (Hotel updatedHotel : updatedHotelList) {
//            long actualHotelId = updatedHotel.getHotelId();
//            Hotel actualHotel = hotelDao.getById(actualHotelId);
//            actualHotelList.add(actualHotel);
//        }
//        assertEquals(updatedHotelList, actualHotelList);
//        hotelDao.updateList(expectedHotelList);
//    }
//
//    @Test
//    public void testGetByHotelName() throws Exception {
//        String hotelName = expectedHotel.getHotelName();
//        Hotel actualHotel = hotelDao.getByHotelName(hotelName);
//        assertEquals(expectedHotel, actualHotel);
//    }
//
//    @Test
//    public void testGetByCountry() throws Exception {
//        String country = expectedHotel.getLocation().getCountry();
//        List<Hotel> actualHotelList = hotelDao.getByCountry(country);
//        assertEquals(expectedHotelListByCountry, actualHotelList);
//    }
//
//    @Test
//    public void testGetByCity() throws Exception {
//        String city = expectedHotel.getLocation().getCity();
//        List<Hotel> actualHotelList = hotelDao.getByCity(city);
//        assertEquals(expectedHotelListByCity, actualHotelList);
//    }

}
