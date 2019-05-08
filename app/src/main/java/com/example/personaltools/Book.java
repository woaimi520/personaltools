package com.example.personaltools;

import java.io.Serializable;
import java.util.jar.Attributes;

public class Book  {
    private String Name;

    public Book(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
