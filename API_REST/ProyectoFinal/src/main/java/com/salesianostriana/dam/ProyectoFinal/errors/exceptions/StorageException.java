package com.salesianostriana.dam.ProyectoFinal.errors.exceptions;

public class StorageException extends RuntimeException{

    public StorageException(String message, Exception e) {
        super(message, e);
    }

    public StorageException(String message) {
        super(message);
    }
}

