package com.bezkoder.springjwt.models;


import javax.persistence.*;

@Entity
@Table(name = "products" )
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String content;
    @Column
    private int price;
    public Product(String name, String content, int price) {
        super();
        this.name = name;
        this.content = content;
        this.price = price;
    }
    public Product() {
        super();
    }
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", content=" + content + ", price="
                + price + "]";
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
