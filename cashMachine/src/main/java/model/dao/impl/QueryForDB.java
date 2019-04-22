package model.dao.impl;

public interface QueryForDB {
    String CHECK_CREATE_CHECK1 = "insert into cashmachine.check (idCheck, price) values (?,?)";
    String CHECK_CREATE_CHECK2 = "insert into worker_has_check (worker_idWorker, check_idCheck) values (?,?)";
    String CHECK_ADD_PRODUCT_TO_CHECK ="insert into check_has_product (check_idCheck, product_idProduct, product_attributeValue) values (?,?,?)";
    String CHECK_UPDATE1 = "update cashmachine.check set price=(?) where idCheck=(?)";
    String CHECK_UPDATE2 = "update cashmachine.check_has_product set product_attributeValue=(?) where check_idCheck=(?) and product_idProduct=(?)";
    String CHECK_DELETE1 = "delete from cashmachine.check_has_product where check_idCheck = (?);";
    String CHECK_DELETE2 = "delete from cashmachine.worker_has_check where check_idCheck = (?);";
    String CHECK_DELETE3 = "delete from cashmachine.check where idCheck = (?)";
    String CHECK_DELETE_PRODUCT_FROM_CHECK = "delete from cashmachine.check_has_product where check_idCheck = (?) and product_idProduct = (?)";
    String CHECK_GET_Z_REPORT = "select product_idProduct, sum(product_attributeValue) from check_has_product group by product_idProduct";
    String CHECK_GET_X_REPORT = "select worker_has_check.worker_idWorker, sum(cashmachine.check.price)\n" +
            " from cashmachine.check, worker_has_check\n" +
            " where cashmachine.check.idCheck=worker_has_check.check_idCheck group by worker_has_check.worker_idWorker";
    String CHECK_FIND_BY_ID = "select cashmachine.check.idCheck, cashmachine.check.price, worker_has_check.worker_idWorker, \n" +
            "check_has_product.product_idProduct, check_has_product.product_attributeValue\n" +
            " from cashmachine.check, worker_has_check, check_has_product\n" +
            "where cashmachine.check.idCheck = (?) and cashmachine.check.idCheck=worker_has_check.check_idCheck and cashmachine.check.idCheck = check_has_product.check_idCheck order by idCheck";
    String CHECK_FIND_ALL ="select cashmachine.check.idCheck, cashmachine.check.price, worker_has_check.worker_idWorker, \n" +
            "check_has_product.product_idProduct, check_has_product.product_attributeValue\n" +
            " from cashmachine.check, worker_has_check, check_has_product\n" +
            "where cashmachine.check.idCheck=worker_has_check.check_idCheck and cashmachine.check.idCheck = check_has_product.check_idCheck order by idCheck";


    String PRODUCT_CREATE1 = "insert into product (idProduct, name, price) values (?,?,?)";
    String PRODUCT_CREATE2 = "insert into product_attribute (idProduct, attribute_name, attribute_value) values (?,?,?)";
    String PRODUCT_UPDATE1 = "update cashmachine.product set name=(?), price=(?) where idProduct=(?)";
    String PRODUCT_UPDATE2 = "update cashmachine.product_attribute set attribute_name=(?), attribute_value=(?) where idProduct=?";
    String PRODUCT_FIND_BY_ID = "select * from product left join product_attribute using(idProduct) where idProduct=?";
    String PRODUCT_FIND_BY_ID_FOR_SALE = "select * from product left join product_attribute using(idProduct) where idProduct=? and attribute_value>=?";
    String PRODUCT_FIND_ALL = "select * from product left join product_attribute using(idProduct) order by idProduct";

    String USER_FIND_BY_LOGIN = "select * from worker where login = ?";
    String USER_CREATE = "insert into worker (idWorker, name, role, login, password) values (?,?,?,?,?)";
    String USER_FIND_BY_ID = "select * from worker where idWorker=?";
    String USER_FIND_ALL = "select * from worker";
    String USER_DELETE = "delete from worker where idWorker = ?";
}
