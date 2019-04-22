package model.entity;

import java.util.Map;
import java.util.Objects;

public class Check {
    private int idCheck;
    private Map<Integer, Integer> products;
    private int idWorker;
    private int price;

    public Check(int idCheck, Map<Integer, Integer> products, int idWorker, int price) {
        this.idCheck = idCheck;
        this.products = products;
        this.idWorker = idWorker;
        this.price = price;
    }

    public Check() {
    }

    public int getidCheck() {
        return idCheck;
    }

    public void setidCheck(int idCheck) {
        this.idCheck = idCheck;
    }

    public Map<Integer, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Integer> products) {
        this.products = products;
    }

    public int getidWorker() {
        return idWorker;
    }

    public void setidWorker(int idWorker) {
        this.idWorker = idWorker;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Check{" +
                "idCheck=" + idCheck +
                ", products=" + products +
                ", worker=" + idWorker +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Check check = (Check) o;
        return idCheck == check.idCheck &&
                idWorker == check.idWorker &&
                price == check.price &&
                Objects.equals(products, check.products);
    }

    @Override
    public int hashCode() {
        return Math.abs(Objects.hash(products, idWorker, price))%1000000;
    }
}
