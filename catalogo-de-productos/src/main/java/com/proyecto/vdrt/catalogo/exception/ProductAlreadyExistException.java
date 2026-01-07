package com.proyecto.vdrt.catalogo.exception;

public class ProductAlreadyExistException extends RuntimeException {

    public ProductAlreadyExistException(){
        super("El nombre del producto ya se encuntra registrado");
    }

}
