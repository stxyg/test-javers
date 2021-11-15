package com.cnn.testjavers.demo.pojo;

import lombok.Data;

@Data
public class Cat {
    //简单属性
    private String name;
    private Integer age;
    //对象属性
    private Home catHome;

    public Cat(String name, Integer age) {
        this.name = name;
        this.age = age;
    }



}
