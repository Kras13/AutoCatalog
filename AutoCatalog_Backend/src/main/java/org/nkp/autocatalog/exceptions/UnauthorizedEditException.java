package org.nkp.autocatalog.exceptions;

public class UnauthorizedEditException extends RuntimeException{
    public UnauthorizedEditException(String message){
        super(message);
    }
}
