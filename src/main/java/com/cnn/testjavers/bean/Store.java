package com.cnn.testjavers.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * drop table store; create table store( id int primary key auto_increment , name varchar(20), address varchar(30),
 * zipCode int );
 * 
 * @author ningning.cheng
 * @date 2021/10/18
 **/
@Data
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private Address address;

    @OneToMany(
            mappedBy = "store",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Product> products = new ArrayList<>();


    public void addProduct(Product product) {
        product.setStore(this);
        this.products.add(product);
    }

    public Store(String name, Address address) {
        this.name = name;
        this.address = address;
    }



    public Store() {
    }
}
