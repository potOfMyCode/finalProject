package model.dao;

import model.entity.Check;
import model.entity.Product;

import java.util.Map;

public interface CheckDao extends GenereicDao<Check>{
    void addProductToCheck(Check check, Product product);
    void deleteProductFromCheck(Check check, int id, int attribute_value);
    Map<Integer, Integer> getZReport();
    Map<String, Integer> getXReport();
}
