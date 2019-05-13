package nl.rug.oop.introduction;

import java.io.Serializable;

abstract class Inspectable implements Serializable {
    // Attributes
    private static final long serialVersionUID = 1L;
    private String description;


    // Constructor
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
