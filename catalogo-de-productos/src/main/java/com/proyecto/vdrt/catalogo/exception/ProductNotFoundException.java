package com.proyecto.vdrt.catalogo.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException (){
        this("El Producto no fue encontrado");
    }

    public ProductNotFoundException (String message){
        super(message);
    }
}
