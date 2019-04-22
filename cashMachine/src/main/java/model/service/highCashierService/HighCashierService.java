package model.service.highCashierService;

import model.dao.CheckDao;
import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entity.Check;
import model.entity.Product;
import model.entity.myException.ProductNotExistException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HighCashierService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Check> getAllChecks(){
        try (CheckDao dao = daoFactory.createCheckDao()){
            return dao.findAll();
        }
    }

    public void cancelCheck(int id){
        try (CheckDao dao = daoFactory.createCheckDao()){
            dao.delete(id);
        }
    }

    public void cancelProductFromCheck(Check check, int id, int attribute_value) throws ProductNotExistException {
        try (CheckDao dao = daoFactory.createCheckDao()){
            dao.deleteProductFromCheck(check, id, attribute_value);
        }
    }

    public Check getCurrentCheck(int id){
        Check check;
        try (CheckDao dao = daoFactory.createCheckDao()){
            check = dao.findById(id);
        }
        return check;
    }

    public void returnMoney(){}


    public Map<String, Integer> makeXreport(){
        try (CheckDao dao = daoFactory.createCheckDao()){
            return dao.getXReport();
        }
    }

    public Map<Integer, Integer> makeZReport(){
        Map<Integer, Integer> products = new HashMap<>();
        try (CheckDao dao = daoFactory.createCheckDao()){
            products = dao.getZReport();
        }
        return products;
    }

    public Product getProduct(int id) throws ProductNotExistException {
        Product product;
        try (ProductDao productDao = daoFactory.createProductDao()) {
            product = productDao.findById(id);
        }
        return product;
    }
}
