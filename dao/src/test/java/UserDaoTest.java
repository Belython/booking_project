import by.kanarski.booking.dao.interfaces.IUserDao;
import by.kanarski.booking.entities.User;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
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

    private User expectedUser;
    private List<User> expectedUserList;
    private User newUser;
    private User updatedUser;

//    @Before
//    public void setUp() {
//        expectedUser = TestEntityBuilder.EXPECTED_USER;
//        expectedUserList = TestEntityBuilder.EXPECTED_USER_LIST;
//        newUser = TestEntityBuilder.NEW_USER;
//        updatedUser = TestEntityBuilder.UPDATED_USER;
//    }

    @After
    public void tearDown() {
        expectedUserList = null;
        expectedUser = null;
        newUser = null;
        updatedUser = null;
    }

}
