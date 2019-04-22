package model.dao.mapper;

import model.entity.Check;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CheckMapper implements ObjectMapper<Check>{
    @Override
    public Check extractFromResultSet(ResultSet rs) throws SQLException {
        Map<Integer, Integer> products = new HashMap<>();
        Check check = new Check();
        check.setidCheck(rs.getInt("idCheck"));
        check.setPrice(rs.getInt("price"));
        check.setidWorker(rs.getInt("worker_idWorker"));
        products.put(rs.getInt("product_idProduct"), rs.getInt("product_attributeValue"));
        boolean flag = true;

        while (flag){
            if (rs.next() && rs.getInt("idCheck") == check.getidCheck()){
                products.put(rs.getInt("product_idProduct"), rs.getInt("product_attributeValue"));
            }else {
                flag = false;
            }
        }
        rs.previous();
        check.setProducts(products);
        return check;
    }

    @Override
    public Check makeUnique(Map<Integer, Check> existing, Check entity) {
        existing.putIfAbsent(entity.getidCheck(), entity);

        return existing.get(entity.getidCheck());
    }
}
