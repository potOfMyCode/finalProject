package dao;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entity.Product;
import model.entity.myException.ProductNotExistException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class JDBCProductFactoryTest {
    private ProductDao factory;

    @Before
    public void init(){
        factory = DaoFactory.getInstance().createProductDao();
    }

    @Test
    public void createTest(){
        Product product = new Product();
        product.setName("testProduct");
        product.setPrice(20);
        product.setAttribute("weight");
        product.setAttribute_value(15);
        product.setidProduct(product.hashCode());

        assertTrue(factory.create(product));
    }

    @Test(expected = ProductNotExistException.class)
    public void finByIdTest() {
        assertTrue(factory.findById(100) != null);
    }

    @Test
    public void updateTest(){
        Product product = factory.findById(1);
        product.setPrice(11);

        assertTrue(factory.update(product));
    }

    @Test
    public void finByIdForSaleTest() {
        assertTrue(factory.findByIdForSale(1, 2) != null);
    }

    @Test
    public void getAllProductsTest() {
        Assert.assertNotNull(factory.findAll());
    }

    @After
    public void after() {
        factory.close();
    }
}
