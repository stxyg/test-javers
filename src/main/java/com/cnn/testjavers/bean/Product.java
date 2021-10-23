package com.cnn.testjavers.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * drop  table  product;
 * create   table product(
 *   id int ,
 *  name varchar(10),
 * count  int,
 * store_id  int
 * );
 * @author ningning.cheng
 * @date 2021/10/18
 **/
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String name;
    private  Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private  Store store;

    public Product(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    public Product() {
    }
}
