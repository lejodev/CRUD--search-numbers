package com.example.numerostelefonicos;

public class Contact {

    String name;
    String number;

    public Contact(String name, String number){

        this.name = name;
        this.number = number;

    }

    // Setters
    public void setName(String name){
        this.name = name;
    }

    public void setNumber(String number){
        this.number = number;
    }

    // Getters
    public String getName(){
        return name;
    }

    public String getNumber(){
        return number;
    }

}

