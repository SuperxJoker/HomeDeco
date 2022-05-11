package com.user.homedeco.exceptions;

public class EmptyFieldException extends Exception{

    public EmptyFieldException() {
        super("All fields are required!");
    }

}
