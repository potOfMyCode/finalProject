package model.dao.mapper;

import model.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ProductMapper implements ObjectMapper<Product>{
    @Override
    public Product extractFromResultSet(ResultSet rs) throws SQLException {
        Product product = new Product();

        product.setidProduct(rs.getInt("idProduct"));
        product.setName(rs.getString("product.name"));
        product.setPrice(rs.getInt("product.price"));
        product.setAttribute(rs.getString("attribute_name"));
        product.setAttribute_value(rs.getInt("attribute_value"));

        return product;
    }

    @Override
    public Product makeUnique(Map<Integer, Product> existing, Product entity) {
        existing.putIfAbsent(entity.getidProduct(), entity);

        return existing.get(entity.getidProduct());
    }
}
