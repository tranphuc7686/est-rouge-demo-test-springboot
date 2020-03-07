package com.example.estrougedemospringboot.models;

public enum StatusEnum {
    PLANNING(0, "Planning"),

    DOING(1, "Complete"),

    COMPLETE(2, "Complete");


    /** The value. */
    public final int value;

    /** The name. */
    public final String name;


    private StatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
