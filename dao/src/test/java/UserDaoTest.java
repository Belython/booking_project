import by.kanarski.booking.constants.SearchParameter;
import by.kanarski.booking.dao.interfaces.IUserDao;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.testingUtils.pre.util.TestEntityBuilder;
import by.kanarski.booking.utils.filter.SearchFilter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/dao-test-config.xml")
@Transactional(propagation = Propagation.SUPPORTS)
public class UserDaoTest extends Assert{

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private IUserDao userDao;

    private Session session;

    private User expectedUser;
    private List<User> expectedUserList;
    private List<User> updatedUserList;
    private List<User> newUserList;
    private User newUser;
    private User updatedUser;

    @Before
    public void setUp() throws Exception {
        expectedUser = TestEntityBuilder.EXPECTED_USER;
        expectedUserList = TestEntityBuilder.EXPECTED_USER_LIST;
        updatedUserList = TestEntityBuilder.UPDATED_USER_LIST;
        newUserList = TestEntityBuilder.NEW_USER_LIST;
        newUser = TestEntityBuilder.NEW_USER;
        updatedUser = TestEntityBuilder.UPDATED_USER;
    }

    @After
    public void tearDown() throws Exception {
        expectedUserList = null;
        expectedUser = null;
        newUser = null;
        updatedUser = null;
    }

    @Test
    public void testAdd() throws Exception {
        userDao.add(newUser);
        User actual = userDao.getById(newUser.getUserId());
        assertEquals(newUser, actual);
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
    public void testUpdate() throws Exception {
        userDao.update(updatedUser);
        User actual = userDao.getById(updatedUser.getUserId());
        assertEquals(updatedUser, actual);
    }

    @Test
    public void testDelete() throws Exception {
        userDao.add(newUser);
        userDao.delete(newUser);
        User deleted = userDao.getById(newUser.getUserId());
        assertNull(deleted);
    }

    @Test
    public void testAddList() throws Exception {
        userDao.addList(newUserList);
        flushAndClearSession();
        SearchFilter searchFilter = SearchFilter.createBasicIlikeFilter(SearchParameter.FIRSTNAME,
                "testFirstNameNew");
        List<User> actual = userDao.getListByFilter(searchFilter, 0, 100);
        assertArrayEquals(actual.toArray(), newUserList.toArray());
        deleteList(actual);
    }

    @Test
    public void testUpdateList() throws Exception {
        userDao.updateList(updatedUserList);
        flushAndClearSession();
        SearchFilter searchFilter = SearchFilter.createBasicIlikeFilter(SearchParameter.FIRSTNAME,
                "testFirstNameUpdated");
        List<User> actual = userDao.getListByFilter(searchFilter, 0, 100);
        assertArrayEquals(actual.toArray(), updatedUserList.toArray());
        flushAndClearSession();
        userDao.updateList(expectedUserList);
        flushAndClearSession();
    }

    @Test
    public void testGetAll() throws Exception {
        List<User> actual = userDao.getAll();
//        flushAndClearSession();
        assertArrayEquals(actual.toArray(), expectedUserList.toArray());
//        flushAndClearSession();
    }

    private void flushAndClearSession() {
        if (session == null) {
            session = sessionFactory.getCurrentSession();
        }
        session.flush();
        session.clear();
    }

    private void deleteList(List<User> userList) throws DaoException {
        for (User user : userList) {
            userDao.delete(user);
        }
        flushAndClearSession();
    }

}
