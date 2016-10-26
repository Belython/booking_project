import by.kanarski.booking.dao.impl.UserDao;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.DaoException;
import pre.util.TestEntityBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserDaoTest extends Assert{

    private UserDao userDao = UserDao.getInstance();

    private User expectedUser;
    private List<User> expectedUserList;
    private User newUser;
    private User updatedUser;

    @Before
    public void setUp() {
//        expectedUser = TestEntityBuilder.EXPECTED_USER;
//        expectedUserList = TestEntityBuilder.EXPECTED_USER_LIST;
//        newUser = TestEntityBuilder.NEW_USER;
//        updatedUser = TestEntityBuilder.UPDATED_USER;
    }

    @After
    public void tearDown() {
        expectedUserList = null;
        expectedUser = null;
        newUser = null;
        updatedUser = null;
    }

    @Test
    public void testGetInstance() throws Exception {
        UserDao instance1 = UserDao.getInstance();
        UserDao instance2 = UserDao.getInstance();
        assertEquals(instance1.hashCode(), instance2.hashCode());
    }

    @Test
    public void testAdd() throws Exception {
        User expected = userDao.add(newUser);
        User actual = userDao.getById(expected.getUserId());
        userDao.delete(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetById() throws Exception {
        try {
            User actual = userDao.getById(expectedUser.getUserId());
            assertEquals(expectedUser, actual);
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void testGetAll() throws Exception {
        List<User> actual = userDao.getAll();
        assertEquals(expectedUserList, actual);
    }

    @Test
    public void testGetByLogin() throws Exception {
        User actual = userDao.getByLogin(expectedUser.getLogin());
        assertEquals(expectedUser, actual);
    }

    @Test
    public void testUpdate() throws Exception {
        userDao.update(updatedUser);
        User actual = userDao.getById(updatedUser.getUserId());
        assertEquals(updatedUser, actual);
        userDao.update(expectedUser);
    }

    @Test
    public void testIsAuthorized() throws Exception {
        String login = expectedUser.getLogin();
        String password = expectedUser.getPassword();
        boolean isAuthorized = userDao.isAuthorized(login, password);
        assertTrue(isAuthorized);
    }

//    @Test
//    public void testIsNewUser() throws Exception {
//        String login = expectedUser.getLogin();
//        boolean isNewUser = userDao.isNewUser(login);
//        assertTrue(isNewUser);
//    }

    @Test(expected = DaoException.class)
    public void testDelete() throws Exception {
        newUser = userDao.add(newUser);
        userDao.delete(newUser);
        User deletedUser = userDao.getById(newUser.getUserId());
        assertNull(deletedUser);
    }

}
