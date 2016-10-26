import by.kanarski.booking.dao.impl.LocationDao;
import by.kanarski.booking.entities.Location;
import by.kanarski.booking.exceptions.DaoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pre.util.TestEntityBuilder;

import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class LocationDaoTest extends Assert {

    private LocationDao locationDao = LocationDao.getInstance();

    private Location expectedLocation;
    private List<Location> expectedLocationList;
    private Location newLocation;
    private Location updatedLocation;

    @Before
    public void setUp() {
//        expectedLocation = TestEntityBuilder.EXPECTED_LOCATION;
//        expectedLocationList = TestEntityBuilder.EXPECTED_LOCATION_LIST;
//        newLocation = TestEntityBuilder.NEW_LOCATION;
//        updatedLocation = TestEntityBuilder.UPDATED_LOCATION;
    }

    @After
    public void tearDown() {
        expectedLocationList = null;
        expectedLocation = null;
        newLocation = null;
        updatedLocation = null;
    }

    @Test
    public void testGetInstance() throws Exception {
        LocationDao instance1 = LocationDao.getInstance();
        LocationDao instance2 = LocationDao.getInstance();
        assertEquals(instance1.hashCode(), instance2.hashCode());
    }

    @Test
    public void testAdd() throws Exception {
        Location expected = locationDao.add(newLocation);
        Location actual = locationDao.getById(expected.getLocationId());
        locationDao.delete(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetById() throws Exception {
        try {
            Location actual = locationDao.getById(expectedLocation.getLocationId());
            assertEquals(expectedLocation, actual);
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void testGetAll() throws Exception {
        List<Location> actual = locationDao.getAll();
        assertEquals(expectedLocationList, actual);
    }

    @Test
    public void testUpdate() throws Exception {
        locationDao.update(updatedLocation);
        Location actual = locationDao.getById(updatedLocation.getLocationId());
        assertEquals(updatedLocation, actual);
        locationDao.update(expectedLocation);
    }

    @Test(expected = DaoException.class)
    public void testDelete() throws Exception {
        newLocation = locationDao.add(newLocation);
        locationDao.delete(newLocation);
        Location deletedLocation = locationDao.getById(newLocation.getLocationId());
        assertNull(deletedLocation);
    }


}
