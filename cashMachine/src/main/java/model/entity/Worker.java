package model.entity;

import model.entity.enums.Role;

import java.util.Objects;

public class Worker {
    private int id;
    private String name;
    private Role role;
    private String login;
    private String password;

    public Worker() {
        this.name="unknown";
        role = Role.UNKNOWN;
    }
    public Worker(int id, String name, Role role, String login, String password) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public Role getRole() {
        return role;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return id == worker.id &&
                name.equals(worker.name) &&
                role == worker.role;
    }

    @Override
    public int hashCode() {
        return Math.abs(Objects.hash(name, role, login, password))%1000000;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
