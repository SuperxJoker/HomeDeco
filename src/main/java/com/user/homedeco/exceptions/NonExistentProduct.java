package com.user.homedeco.exceptions;

public class NonExistentProduct extends Exception{
    public NonExistentProduct()
    {
        super("The selected product doesn't exist");
    }
}
