package com.cnn.testjavers.demo.simple;

import lombok.Data;

@Data
public class Home {
    private  String  address;

    public Home(String address) {
        this.address = address;
    }
}
