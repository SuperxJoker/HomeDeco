package com.user.homedeco.exceptions;

public class CouldNotAnswerException extends Exception{

    public CouldNotAnswerException() {
        super("Post answered or nonexistent!");
    }

}