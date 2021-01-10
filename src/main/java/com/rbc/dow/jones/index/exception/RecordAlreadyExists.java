package com.rbc.dow.jones.index.exception;

public class RecordAlreadyExists extends Exception {

    public RecordAlreadyExists() { }

    public RecordAlreadyExists(String message){
        super(message);
    }
}
