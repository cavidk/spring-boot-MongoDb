package com.example.demo;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address extends org.apache.tomcat.jni.Address {
    private String country;
    private String city;
    private String postCode;
}
