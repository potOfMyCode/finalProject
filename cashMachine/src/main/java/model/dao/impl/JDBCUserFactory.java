package model.dao.impl;

import model.dao.UserDao;
import model.dao.mapper.UserMapper;
import model.entity.Worker;
import model.entity.myException.WorkerNotExistException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

public class JDBCUserFactory implements UserDao {
    private final static Logger LOGGER = Logger.getLogger(JDBCUserFactory.class.getSimpleName());
    private Connection connection;

    public JDBCUserFactory(Connection connection) {
        this.connection = connection;
        LOGGER.debug("Creating instance of " + this.getClass().getName());
    }

    @Override
    public Optional<Worker> findByLogin(String login) {
        Optional<Worker> result = Optional.empty();
        String query = QueryForDB.USER_FIND_BY_LOGIN;
        try(PreparedStatement ps = connection.prepareCall(query)){
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            UserMapper userMapper = new UserMapper();

            if(rs.next()){
                result = Optional.of(userMapper.extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException while findByLoginUser with login: " + login);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean create(Worker worker) {
        String query = QueryForDB.USER_CREATE;
        try(PreparedStatement ps = connection.prepareCall(query)){
            ps.setInt(1, worker.getId());
            ps.setString(2, worker.getName());
            ps.setString(3, worker.getRole().toString().toLowerCase());
            ps.setString(4, worker.getLogin());
            ps.setString(5, worker.getPassword());

            ps.executeUpdate();

            LOGGER.info("User " + worker.getName() + " registered successfully");
            return true;
        } catch (SQLException e) {
            LOGGER.error("SQLException while creating user: " + worker);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Worker entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = QueryForDB.USER_DELETE;
        try(PreparedStatement ps = connection.prepareCall(query)){

            ps.setInt(1, id);

            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            LOGGER.error("SQLException while deleting user with id: " + id);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Worker findById(int id) throws WorkerNotExistException{
        Worker worker = null;
        final String query = QueryForDB.USER_FIND_BY_ID;

        try(PreparedStatement ps = connection.prepareCall(query)){
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();

            while(rs.next()){
                worker = userMapper.extractFromResultSet(rs);
            }
            if (worker==null) {
                LOGGER.error("Worker not exist(id): " + id);
                throw new WorkerNotExistException(id+"");
            }
            return worker;
        } catch (SQLException e) {
            LOGGER.error("SQLException while findById user with id: " + id);
            e.printStackTrace();
        }
        LOGGER.error("Worker not exist(id): " + id);
        throw new WorkerNotExistException(id+"");
}

    @Override
    public List<Worker> findAll() {
        Map<Integer, Worker> workers = new HashMap<>();

        final String query = QueryForDB.USER_FIND_ALL;

        try(Statement st = connection.createStatement()){

            ResultSet rs = st.executeQuery(query);

            UserMapper userMapper = new UserMapper();

            while(rs.next()) {
                Worker worker = userMapper.extractFromResultSet(rs);
                userMapper.makeUnique(workers, worker);
            }

            return new ArrayList<>(workers.values());

        } catch (SQLException e) {
            LOGGER.error("SQLException while findAll users!");
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
