package nl.rug.oop.introduction;

import java.io.Serializable;

abstract class Inspectable implements Serializable {
    // Attributes
    private String description;


    // Constructors
    Inspectable(String description) {
        this.description = description;
    }


    // Getters and setters
    String getDescription() {
        return description;
    }


    // Other methods
    void inspect() {
        System.out.println(this.getDescription());
    }
}
