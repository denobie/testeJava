package com.elotechdenobie.testejava.exceptions;

public class DataBaseException extends RuntimeException{
    public DataBaseException(String exceptionMessage){
        super(exceptionMessage);
    }
}
