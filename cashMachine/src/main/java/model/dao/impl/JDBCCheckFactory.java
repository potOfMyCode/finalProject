package model.dao.impl;

import model.dao.CheckDao;
import model.dao.mapper.CheckMapper;
import model.entity.Check;
import model.entity.Product;
import model.entity.myException.ProductNotExistException;
import model.service.UserService;
import model.service.productMakerService.ProductMakerService;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCCheckFactory implements CheckDao {
    private final static Logger LOGGER = Logger.getLogger(JDBCCheckFactory.class.getSimpleName());
    private Connection connection;

    public JDBCCheckFactory(Connection connection) {
        this.connection = connection;
        LOGGER.debug("Creating instance of " + this.getClass().getName());
    }

    @Override
    public boolean create(Check check) {
        String query1 = QueryForDB.CHECK_CREATE_CHECK1;
        String query2 = QueryForDB.CHECK_CREATE_CHECK2;
        try(PreparedStatement ps1 = connection.prepareCall(query1); PreparedStatement ps2 = connection.prepareCall(query2)){

            ps1.setInt(1, check.getidCheck());
            ps1.setInt(2, check.getPrice());
            ps1.executeUpdate();

            ps2.setInt(1,check.getidWorker());
            ps2.setInt(2, check.getidCheck());
            ps2.executeUpdate();

            return true;
        } catch (SQLException e) {
            LOGGER.error("SQLException while create check: " + check);
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void addProductToCheck(Check check, Product product) {
        String query = QueryForDB.CHECK_ADD_PRODUCT_TO_CHECK;
        try(PreparedStatement ps2 = connection.prepareCall(query)){

            ps2.setInt(1, check.getidCheck());
            ps2.setInt(2, product.getidProduct());
            ps2.setInt(3, check.getProducts().get(product.getidProduct()));
            ps2.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("SQLException while addProductToCheck: " + check + " " + product);
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(Check check) {
        String query1 = QueryForDB.CHECK_UPDATE1;
        String query2 = QueryForDB.CHECK_UPDATE2;
        try(PreparedStatement ps1 = connection.prepareCall(query1);
            PreparedStatement ps2 = connection.prepareCall(query2)){

            ps1.setInt(1, check.getPrice());
            ps1.setInt(2, check.getidCheck());
            ps1.executeUpdate();


            for (Map.Entry<Integer, Integer> entry : check.getProducts().entrySet()) {
                ps2.setInt(1, entry.getValue());
                ps2.setInt(2, check.getidCheck());
                ps2.setInt(3, entry.getKey());
                ps2.executeUpdate();
            }

            return true;
        } catch (SQLException e) {
            LOGGER.error("SQLException while updating check: " + check);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query1 = QueryForDB.CHECK_DELETE1;
        String query2 = QueryForDB.CHECK_DELETE2;
        String query3 = QueryForDB.CHECK_DELETE3;

        try(PreparedStatement ps1 = connection.prepareCall(query1);
            PreparedStatement ps2 = connection.prepareCall(query2);
            PreparedStatement ps3 = connection.prepareCall(query3)){
            connection.setAutoCommit(false);

            ps1.setInt(1, id);
            ps1.executeUpdate();

            ps2.setInt(1, id);
            ps2.executeUpdate();

            ps3.setInt(1, id);
            ps3.executeUpdate();

            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            LOGGER.error("SQLException delete check by id: " + id);
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void deleteProductFromCheck(Check check, int id, int attribute_value) throws ProductNotExistException {
        String query;
        ProductMakerService productMakerService = new ProductMakerService();
        for (Map.Entry<Integer, Integer> entry : check.getProducts().entrySet()) {
            if (entry.getKey() == id){
                if(attribute_value>=entry.getValue()) {
                    check.setPrice(check.getPrice() - entry.getValue() * productMakerService.getProduct(id).getPrice());
                    query = QueryForDB.CHECK_DELETE_PRODUCT_FROM_CHECK;
                    try(PreparedStatement ps1 = connection.prepareCall(query)){

                        ps1.setInt(1, check.getidCheck());
                        ps1.setInt(2, id);
                        ps1.executeUpdate();

                    } catch (SQLException e) {
                        LOGGER.error("SQLException delete product from check: " + check + "id product, attribute_value: " + id + ", " + attribute_value);
                        e.printStackTrace();
                    }
                    update(check);
                    return;
                }
            }
        }
        check.setPrice(check.getPrice()- attribute_value*productMakerService.getProduct(id).getPrice());
        Map<Integer, Integer> products = check.getProducts();
        int newAttr = check.getProducts().get(id) - attribute_value;
        products.remove(id);
        products.put(id, newAttr);
        check.setProducts(products);
        update(check);

    }

    @Override
    public Map<Integer, Integer> getZReport() {
        Map<Integer, Integer> products = new HashMap<>();

        final String query = QueryForDB.CHECK_GET_Z_REPORT;

        try(Statement st = connection.createStatement()){

            ResultSet rs =st.executeQuery(query);

            while(rs.next()){
                int idProduct = rs.getInt("product_idProduct");
                int sum = rs.getInt("sum(product_attributeValue)");
                products.put(idProduct, sum);

            }

            return products;
        } catch (SQLException e) {
            LOGGER.error("SQLException while making Z-Report." );
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public  Map<String, Integer> getXReport() {
        Map<String, Integer> workers = new HashMap<>();
        final String query = QueryForDB.CHECK_GET_X_REPORT;

        try(Statement st = connection.createStatement()){

            ResultSet rs =st.executeQuery(query);

            UserService userService = new UserService();
            while(rs.next()){
                int idWorker = rs.getInt("worker_idWorker");
                int sum = rs.getInt("sum(cashmachine.check.price)");

                workers.put(userService.getById(idWorker).getName(), sum);

            }

        } catch (SQLException e) {
            LOGGER.error("SQLException while making X-Report. ");
            e.printStackTrace();
            return null;
        }
        return workers;
    }

    @Override
    public Check findById(int id) {
        Check result = null;
        String query = QueryForDB.CHECK_FIND_BY_ID;
        try(PreparedStatement ps = connection.prepareCall(query)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            CheckMapper checkMapper = new CheckMapper();

            if(rs.next()){
                result = checkMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException while findById check with id: " + id);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Check> findAll() {
        Map<Integer, Check> products = new HashMap<>();

        final String query = QueryForDB.CHECK_FIND_ALL;

        try(Statement st = connection.createStatement()){

            ResultSet rs =st.executeQuery(query);

            CheckMapper checkMapper = new CheckMapper();

            while(rs.next()){
                Check check =checkMapper.extractFromResultSet(rs);
                checkMapper.makeUnique(products, check);
            }

            return new ArrayList<>(products.values());
        } catch (SQLException e) {
            LOGGER.error("SQLException while findAll checks!");
            e.printStackTrace();
            return null;
        }
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
