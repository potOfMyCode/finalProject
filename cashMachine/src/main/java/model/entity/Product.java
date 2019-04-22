package model.entity;

import java.util.Objects;

public class Product {
    private int idProduct;
    private String name;
    private int price;
    private String attribute;
    private int attribute_value;

    public Product(int idProduct, String name, int price, String attribute, int attribute_value) {
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.attribute = attribute;
        this.attribute_value = attribute_value;
    }

    public Product() {}

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public int getAttribute_value() {
        return attribute_value;
    }

    public void setAttribute_value(int attribute_value) {
        this.attribute_value = attribute_value;
    }

    public String getName() {
        return name;
    }

    public int getidProduct() {
        return idProduct;
    }

    public void setidProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + idProduct +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", attribute='" + attribute + '\'' +
                ", attribute_value=" + attribute_value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return idProduct == product.idProduct &&
                price == product.price &&
                attribute_value == product.attribute_value &&
                name.equals(product.name) &&
                attribute.equals(product.attribute);
    }

    @Override
    public int hashCode() {
        return Math.abs(Objects.hash( name, price, attribute, attribute_value))%1000000;
    }
}
