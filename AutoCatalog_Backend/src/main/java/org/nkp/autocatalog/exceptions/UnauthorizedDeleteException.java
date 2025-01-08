package org.nkp.autocatalog.exceptions;

public class UnauthorizedDeleteException extends RuntimeException{
    public UnauthorizedDeleteException(String message){
        super(message);
    }
}
