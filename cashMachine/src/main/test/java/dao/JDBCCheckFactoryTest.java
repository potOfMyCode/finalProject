package dao;

import model.dao.CheckDao;
import model.dao.DaoFactory;
import model.entity.Check;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

public class JDBCCheckFactoryTest {
    private CheckDao factory;
    @Before
    public void init(){
        factory = DaoFactory.getInstance().createCheckDao();
    }

    @Test
    public void createTest(){
        Check check = new Check();
        Map<Integer, Integer> products= new HashMap<>();
        products.put(1 , 2);
        products.put(7, 1);
        products.put(3, 1);
        check.setProducts(products);
        check.setidWorker(3);
        check.setPrice(300);
        check.setidCheck(check.hashCode());
        assertTrue(factory.create(check));
    }
    @Test
    public void updateTest(){
        Check check = new Check();
        Map<Integer, Integer> products= new HashMap<>();
        products.put(1 , 2);
        products.put(7, 1);
        products.put(3, 1);
        check.setProducts(products);
        check.setidWorker(3);
        check.setPrice(300);
        check.setidCheck(check.hashCode());
        assertTrue(factory.update(check));
    }

    @Test
    public void getZReportTest(){
        Assert.assertNotNull(factory.getZReport());
    }
    @Test
    public void getXReportTest(){
        Assert.assertNotNull(factory.getXReport());
    }

    @Test
    public void getAllChecksTest() {
        Assert.assertNotNull(factory.findAll());
    }

    @After
    public void after() {
        factory.close();
    }
}
