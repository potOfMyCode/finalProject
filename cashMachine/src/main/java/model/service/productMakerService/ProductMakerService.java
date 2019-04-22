package model.service.productMakerService;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entity.Product;
import model.entity.myException.ProductNotExistException;

import java.util.List;

public class ProductMakerService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void createProduct(Product product){
        try (ProductDao productDao = daoFactory.createProductDao()) {
            productDao.create(product);
        }
    }

    public void updateProduct(Product product){
        try(ProductDao productDao = daoFactory.createProductDao()){
            productDao.update(product);
        }
    }

    public List<Product> getAllProducts(){
        try (ProductDao dao = daoFactory.createProductDao()){
            return dao.findAll();
        }
    }

    public Product getProduct(int id) throws ProductNotExistException {
        Product product;
        try (ProductDao productDao = daoFactory.createProductDao()) {
            product = productDao.findById(id);
        }
        return product;
    }

    public boolean validateData(Product product) {
        return false;
    }
}
