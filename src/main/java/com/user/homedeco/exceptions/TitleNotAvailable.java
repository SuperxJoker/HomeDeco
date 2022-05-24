package com.user.homedeco.exceptions;

public class TitleNotAvailable extends Exception{
    public TitleNotAvailable()
    {
        super("This title has been used");
    }
}
