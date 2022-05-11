package com.user.homedeco.exceptions;

public class UsernameNotAvailable extends Exception{
    public UsernameNotAvailable()
    {
        super("This email address is not available.");
    }
}
