import by.kanarski.booking.dao.interfaces.IUserDao;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.DaoException;
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
import pre.util.TestEntityBuilder;

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
    private User newUser;
    private User updatedUser;

    @Before
    public void setUp() {
        expectedUser = TestEntityBuilder.EXPECTED_USER;
        expectedUserList = TestEntityBuilder.EXPECTED_USER_LIST;
        newUser = TestEntityBuilder.NEW_USER;
        updatedUser = TestEntityBuilder.UPDATED_USER;
    }

    @After
    public void tearDown() {
        expectedUserList = null;
        expectedUser = null;
        newUser = null;
        updatedUser = null;
    }

    @Test
    public void testAdd() throws Exception {
        userDao.add(newUser);
        flushAndClearSession();
        User actual = userDao.getById(newUser.getUserId());
        flushAndClearSession();
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
        flushAndClearSession();
    }

    @Test
    public void testUpdate() throws Exception {
        userDao.update(updatedUser);
        flushAndClearSession();
        User actual = userDao.getById(updatedUser.getUserId());
        assertEquals(updatedUser, actual);
        flushAndClearSession();
        userDao.update(expectedUser);
        flushAndClearSession();
    }

    private void flushAndClearSession() {
        if (session == null) {
            session = sessionFactory.getCurrentSession();
        }
        session.flush();
        session.clear();
    }

}
