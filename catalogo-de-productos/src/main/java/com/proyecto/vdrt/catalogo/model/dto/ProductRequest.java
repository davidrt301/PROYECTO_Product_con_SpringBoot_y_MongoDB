package com.proyecto.vdrt.catalogo.model.dto;


import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProductRequest {

    @NotBlank(message = "El  nombre de usuario es obligatorio")
    private String name;
    
    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    @Digits(integer = 10, fraction = 2, message = "El precio debe tener un formato válido (máximo 2 decimales)")
    private Double price;

    @JsonProperty("general_description")
    @NotBlank(message = "El producto debe llevar una descripción obligatoria")
    private String generalDescription;
    
    @NotEmpty(message = "Las especificaciones no pueden estar vacías")
    private Map<String, String> specifications;

    @NotEmpty(message = "La lista de etiquetas no puede estar vacía")
    private List<String> tags;

    public ProductRequest(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getGeneralDescription() {
        return generalDescription;
    }

    public void setGeneralDescription(String generalDescription) {
        this.generalDescription = generalDescription;
    }

    public Map<String, String> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(Map<String, String> specifications) {
        this.specifications = specifications;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}
