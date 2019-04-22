package model.dao.mapper;

import model.entity.Worker;
import model.entity.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<Worker>{
    @Override
    public Worker extractFromResultSet(ResultSet rs) throws SQLException {
        Worker worker = new Worker();

        worker.setId(rs.getInt("idWorker"));
        worker.setName(rs.getString("worker.name"));
        worker.setRole(Role.valueOf(rs.getString("worker.role").toUpperCase()));
        worker.setLogin(rs.getString("worker.login"));
        worker.setPassword(rs.getString("worker.password"));
        return worker;
    }

    @Override
    public Worker makeUnique(Map<Integer, Worker> existing, Worker entity) {
        existing.putIfAbsent(entity.getId(), entity);

        return existing.get(entity.getId());
    }
}
