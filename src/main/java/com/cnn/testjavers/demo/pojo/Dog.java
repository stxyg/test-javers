package com.cnn.testjavers.demo.pojo;

import org.javers.core.metamodel.annotation.Id;
import org.javers.core.metamodel.annotation.TypeName;

import lombok.Data;

@Data
@TypeName("dog")
public class Dog {
    //简单属性
    @Id
    private String name;
    private Integer age;
    //对象属性
    private Home home;

    public Dog(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Dog(String name, Integer age, Home home) {
        this.name = name;
        this.age = age;
        this.home = home;
    }
}
