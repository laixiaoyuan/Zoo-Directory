package edu.xlaiscu.zoodirectory;

/**
 * Created by Lexie on 4/29/16.
 */

public class Animal {
    private String name;
    private String filename;
    private String description;

    public Animal(String name, String filename, String description) {
        this.name = name;
        this.filename = filename;
        this.description = description;

    }

    public String getName() {
        return name;
    }

    public String getFilename() {
        return filename;
    }

    public String getDescription() {
        return description;
    }
}