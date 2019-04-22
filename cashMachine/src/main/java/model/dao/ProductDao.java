package model.dao;

import model.entity.Product;

public interface ProductDao extends GenereicDao<Product>{
    Product findByIdForSale(int id, int attribute_value);
}
