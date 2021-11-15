package com.cnn.testjavers.demo.pojo;

import org.javers.core.metamodel.annotation.Id;
import org.javers.core.metamodel.annotation.TypeName;

import lombok.Data;
import lombok.NoArgsConstructor;

@TypeName("person")
@Data
@NoArgsConstructor
public class Person {

    @Id
    private String name;
    private Person father;
    private Home home;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Person father) {
        this.name = name;
        this.father = father;
    }

    public Person(String name, Person father, Home home) {
        this.name = name;
        this.father = father;
        this.home = home;
    }
}
