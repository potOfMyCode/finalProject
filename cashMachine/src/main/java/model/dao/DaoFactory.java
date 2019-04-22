package model.dao;

import model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract CheckDao createCheckDao();

    public abstract ProductDao createProductDao();

    public abstract UserDao createUserDao();

    public static DaoFactory getInstance(){
        if(daoFactory == null){
            synchronized (DaoFactory.class){
                if(daoFactory == null)
                    daoFactory = new JDBCDaoFactory();
            }
        }
        return daoFactory;
    }
}
