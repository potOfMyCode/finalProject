package model.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;
    private final static Logger LOGGER = Logger.getLogger(ConnectionPoolHolder.class.getSimpleName());
    public static DataSource getDataSource(){
        if(dataSource==null){
            synchronized (ConnectionPoolHolder.class){
                if(dataSource == null){
                    try{
                        Properties properties = new Properties();

                        properties.load(new FileInputStream(
                                "C:\\java\\javaTools\\git\\Projects\\finalProject\\cashMachine\\src\\main\\java\\resources\\db.property"));

                        Class.forName(properties.getProperty("db.connection.driver"));
                        BasicDataSource ds = new BasicDataSource();
                        ds.setUrl(properties.getProperty("db.connection.url"));
                        ds.setUsername(properties.getProperty("db.connection.username"));
                        ds.setPassword(properties.getProperty("db.connection.password"));
                        ds.setMinIdle(5);
                        ds.setMaxIdle(10);
                        ds.setMaxOpenPreparedStatements(100);
                        dataSource = ds;
                        LOGGER.info("WE ARE IN CONNECTION POOL HOLDER");
                    }catch (IOException | ClassNotFoundException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return dataSource;
    }
}
