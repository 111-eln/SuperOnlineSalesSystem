package com.atmosware.SuperOnline.OrderService.core.exceptions.types;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }
}