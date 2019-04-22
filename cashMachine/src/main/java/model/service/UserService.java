package model.service;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.Worker;
import model.entity.myException.NotUniqLoginException;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class UserService {
    private final static Logger LOGGER = Logger.getLogger(UserService.class.getSimpleName());
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<Worker> login(String login) {
        Optional<Worker> result;

        try (UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.findByLogin(login);
        }

        return result;
    }

    public void addCashierToDB(Worker worker){
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.create(worker);
        }
    }

    public List<Worker> getAllWorkers(){
        try (UserDao dao = daoFactory.createUserDao()){
            return dao.findAll();
        }
    }

    public Worker getById(int id){
        try (UserDao dao = daoFactory.createUserDao()){
            return dao.findById(id);
        }
    }

    public boolean validateData(Worker worker) throws IOException, NotUniqLoginException {
        Properties properties = new Properties();

        properties.load(new FileInputStream(
                "C:\\java\\javaTools\\git\\Projects\\hometask\\finalProject\\cashMachine\\src\\main\\java\\resources\\regex.properties"));
        String loginRegex = properties.getProperty("login.regex");
        String passwordRegex = properties.getProperty("password.regex");
        String nameRegex = properties.getProperty("name.regex");

         boolean result = worker.getName().matches(nameRegex)
                && worker.getLogin().matches(loginRegex)
                && worker.getPassword().matches(passwordRegex);
         Optional<Worker>  w = login(worker.getLogin());
         if (w.isPresent()) {
             LOGGER.error("NotUniqLoginException in UserService.validateData(Worker worker)");
             throw new NotUniqLoginException(worker.getLogin());
         }
        return result&&(!w.isPresent());
    }
}
