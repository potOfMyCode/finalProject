package model.dao;

import model.entity.Worker;

import java.util.Optional;

public interface UserDao extends GenereicDao<Worker> {
    Optional<Worker> findByLogin(String login);
}
