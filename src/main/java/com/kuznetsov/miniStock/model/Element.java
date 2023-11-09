package com.kuznetsov.miniStock.model;

import jakarta.validation.constraints.*;

import java.util.Objects;

public class Element {
    private Integer id;
    @Size(min = 3,max = 100,message = "Name should be more than 2, and be less than 100")
    @NotBlank(message = "Name should not be empty")
    private String name;
    @Min(value = 1, message = "Price should be more than 0")
    @Max(value = 400000,message = "Price should be less than 400 000")
    @NotNull(message = "Price should not be null")
    private Integer price;
    @NotNull(message = "Count should not be null")
    private Integer count;

    public Element(Integer id, String name, Integer price, Integer count) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public Element() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Element)) return false;
        Element element = (Element) o;
        return Objects.equals(name, element.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Element{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
