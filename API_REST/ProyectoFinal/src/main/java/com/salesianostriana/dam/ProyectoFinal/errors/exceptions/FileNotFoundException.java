package com.salesianostriana.dam.ProyectoFinal.errors.exceptions;

public class FileNotFoundException extends StorageException{
    public FileNotFoundException(String message, Exception e) {
        super(message, e);
    }

    public FileNotFoundException(String message) {
        super(message);
    }
}
