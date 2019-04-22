package model.service.cashierService;

import model.dao.CheckDao;
import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entity.Check;
import model.entity.Product;
import model.entity.myException.ProductNotExistException;

public class CashierService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public Product getProduct(int id, int attribute_value) throws ProductNotExistException {
        Product product;
        try (ProductDao productDao = daoFactory.createProductDao()) {
            product = productDao.findByIdForSale(id, attribute_value);
        }
        return product;
    }

    public void addProductInCheck(Check check, Product product){
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            checkDao.addProductToCheck(check, product);
        }
    }

    public void createCheck(Check check){
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            checkDao.create(check);
        }
    }

    public void updateCheck(Check check){
        try (CheckDao checkDao = daoFactory.createCheckDao()) {
            checkDao.update(check);
        }
    }
}
