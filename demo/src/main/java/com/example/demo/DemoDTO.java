package com.example.demo;

import lombok.Value;

@Value(staticConstructor = "of" )
public class DemoDTO {

    String name;
    Integer age;

}
