import by.kanarski.booking.dao.interfaces.IBillDao;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.services.interfaces.IBillService;
import by.kanarski.booking.testingUtils.pre.jsonHandler.TestDatabaseFiller;
import by.kanarski.booking.testingUtils.pre.util.TestEntityBuilder;
import by.kanarski.booking.utils.DtoToEntityConverter;
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

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/service-test-config.xml")
@Transactional(propagation = Propagation.SUPPORTS)
public class BillServiceTest extends Assert {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private IBillService billService;
    @Autowired
    private IBillDao billDao;
    @Autowired
    private TestDatabaseFiller testDatabaseFiller;

    private DtoToEntityConverter<Bill, BillDto> billConverter =
            new DtoToEntityConverter<>(Bill.class, BillDto.class);

    private BillDto newBillDto;
    private List<BillDto> expectedBillDtoList;
    private BillDto expectedBillDto;
    private BillDto updatedBillDto;

    @Before
    public void setUp() throws Exception {
//        testDatabaseFiller.addToDatabase();
        Bill newBill = TestEntityBuilder.NEW_BILL;
        billDao.add(newBill);
        newBillDto = billConverter.toDto(TestEntityBuilder.NEW_BILL);
        expectedBillDtoList = billConverter.toDtoList(TestEntityBuilder.EXPECTED_BILL_LIST);
        expectedBillDto = billConverter.toDto(TestEntityBuilder.EXPECTED_BILL);
        updatedBillDto = billConverter.toDto(TestEntityBuilder.UPDATED_BILL);
    }

    @After
    public void tearDown() throws Exception {
//        testDatabaseFiller.cleanTables();
        newBillDto = null;
        expectedBillDtoList = null;
        expectedBillDto = null;
        updatedBillDto = null;
    }

    @Test
    public void add() throws Exception {
//        billService.add(newBillDto);
//        BillDto actual = billService.getById(newBillDto.getBillId());
//        assertEquals(newBillDto, actual);
    }

    @Test
    public void getById() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void getByUserId() throws Exception {

    }

    @Test
    public void makeBill() throws Exception {

    }

    @Test
    public void cancelBooking() throws Exception {

    }

    @Test
    public void payBIll() throws Exception {

    }

    @Test
    public void deleteBill() throws Exception {

    }

}