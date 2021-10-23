package com.cnn.testjavers.bean;

import javax.persistence.Embeddable;

import lombok.Data;

/**
 *
 * @author ningning.cheng
 * @date 2021/10/18
 **/
@Data
@Embeddable
public class Address {
    private String address;
    private Integer zipCode;

    public Address(String address, Integer zipCode) {
        this.address = address;
        this.zipCode = zipCode;
    }

    public Address() {
    }
}
