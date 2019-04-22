package model.dao.impl;

import model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public CheckDao createCheckDao() {
        return new JDBCCheckFactory(getConnection());
    }

    @Override
    public ProductDao createProductDao() {
        return new JDBCProductFactory(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserFactory(getConnection());
    }


    private Connection getConnection(){
        try{
            return dataSource.getConnection();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
