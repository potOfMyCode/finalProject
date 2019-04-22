package model.dao.impl;

import model.dao.ProductDao;
import model.dao.mapper.ProductMapper;
import model.entity.Product;
import model.entity.myException.ProductNotExistException;
import model.entity.myException.ProductOutOfStockException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCProductFactory implements ProductDao {
    private final static Logger LOGGER = Logger.getLogger(JDBCProductFactory.class.getSimpleName());
    private Connection connection;

    JDBCProductFactory(Connection connection) {
        this.connection = connection;
        LOGGER.debug("Creating instance of " + this.getClass().getName());
    }

    @Override
    public boolean create(Product product) {
        String query1 = QueryForDB.PRODUCT_CREATE1;
        String query2 = QueryForDB.PRODUCT_CREATE2;
        try(PreparedStatement ps1 = connection.prepareCall(query1); PreparedStatement ps2 = connection.prepareCall(query2)){
            connection.setAutoCommit(false);
            ps1.setInt(1, product.getidProduct());
            ps1.setString(2, product.getName());
            ps1.setInt(3, product.getPrice());
            ps1.executeUpdate();

            ps2.setInt(1, product.getidProduct());
            ps2.setString(2, product.getAttribute());
            ps2.setInt(3, product.getAttribute_value());
            ps2.executeUpdate();

            connection.commit();

            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            LOGGER.error("SQLException while creating product: " + product);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        String query1 = QueryForDB.PRODUCT_UPDATE1;
        String query2 = QueryForDB.PRODUCT_UPDATE2;
        try(PreparedStatement ps1 = connection.prepareCall(query1); PreparedStatement ps2 = connection.prepareCall(query2)){
            connection.setAutoCommit(false);

            ps1.setString(1, product.getName());
            ps1.setInt(2, product.getPrice());
            ps1.setInt(3, product.getidProduct());
            ps1.executeUpdate();

            ps2.setString(1, product.getAttribute());
            ps2.setInt(2, product.getAttribute_value());
            ps2.setInt(3, product.getidProduct());
            ps2.executeUpdate();

            connection.commit();

            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            LOGGER.error("SQLException while updating product: " + product);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Product findById(int id) throws ProductNotExistException{
        Product result = null;
        String query = QueryForDB.PRODUCT_FIND_BY_ID;
        try(PreparedStatement ps = connection.prepareCall(query)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            ProductMapper productMapper = new ProductMapper();

            if(rs.next()){
                result = productMapper.extractFromResultSet(rs);
            }
            if (result == null) {
                LOGGER.error("Product not exist! (id)=" + id);
                throw new ProductNotExistException(id+"");
            }
            return result;

        } catch (SQLException e) {
            LOGGER.error("SQLException while FindById product with id: " + id);
            e.printStackTrace();
        }
        LOGGER.error("Product not exist! (id)=" + id);
        throw new ProductNotExistException(id+"");
    }
    @Override
    public Product findByIdForSale(int id, int attribute_value) throws ProductNotExistException, ProductOutOfStockException {
        Product result = null;
        String query = QueryForDB.PRODUCT_FIND_BY_ID_FOR_SALE;
        try(PreparedStatement ps = connection.prepareCall(query)){
            ps.setInt(1, id);
            ps.setInt(2, attribute_value);
            ResultSet rs = ps.executeQuery();

            ProductMapper productMapper = new ProductMapper();

            if(rs.next()){
                result = productMapper.extractFromResultSet(rs);
            }
            if (result == null) {
                LOGGER.error("Product out of stock! (id)=" + id);
                throw new ProductOutOfStockException(id+"");
            }
            result.setAttribute_value(result.getAttribute_value()-attribute_value);
            update(result);

            return result;

        } catch (SQLException e) {
            LOGGER.error("SQLException while FindByIdForSale product with id: " + id);
            e.printStackTrace();
        }
        LOGGER.error("Product out of stock! (id)=" + id);
        throw new ProductOutOfStockException(id+"");
    }

    @Override
    public List<Product> findAll() {
        Map<Integer, Product> products = new HashMap<>();

        final String query = QueryForDB.PRODUCT_FIND_ALL;

        try(Statement st = connection.createStatement()){

            ResultSet rs =st.executeQuery(query);

            ProductMapper productMapper = new ProductMapper();

            while(rs.next()){
                Product product =productMapper.extractFromResultSet(rs);
                productMapper.makeUnique(products, product);
            }

            return new ArrayList<>(products.values());
        } catch (SQLException e) {
            LOGGER.error("SQLException while findAllProduct.");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
