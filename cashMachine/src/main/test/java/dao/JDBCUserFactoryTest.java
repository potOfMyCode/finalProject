package dao;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.Worker;
import model.entity.enums.Role;
import model.entity.myException.NotUniqLoginException;
import model.service.UserService;
import org.junit.*;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class JDBCUserFactoryTest {
    private UserDao factory;

    @Before
    public void init(){
        factory = DaoFactory.getInstance().createUserDao();
    }

    @Test
    public void createTest(){
        Worker worker = new Worker();
        worker.setName("Misha");
        worker.setRole(Role.CASHIER);

        worker.setLogin("mishaC4");
        worker.setPassword("1234");
        worker.setId(worker.hashCode());
        assertTrue(factory.create(worker));
    }

    @Test
    public void finByLoginTest() {
        assertTrue(factory.findByLogin("mishaC4").isPresent());
    }

    @Test
    public void deleteTest() {
        assertTrue(factory.delete(factory.findByLogin("mishaC4").get().getId()));
    }

    @Test(expected = NotUniqLoginException.class)
    public void createNotUniqueLoginTest() {
        boolean result = false;
        Worker worker = new Worker();

        worker.setName("Igor");
        worker.setRole(Role.CASHIER);

        worker.setLogin("mishaC4");
        worker.setPassword("1234");
        worker.setId(worker.hashCode());

        UserService userService = new UserService();
        try {
            userService.validateData(worker);
        } catch (IOException e) {
            e.printStackTrace();
        }

        result = factory.create(worker);

        assertFalse(result);
        factory.close();
    }

    @Test
    public void getAllWorkersTest() {
        Assert.assertNotNull(factory.findAll());
    }

    @After
    public void after() {
        factory.close();
    }
}
