package com.proyecto.vdrt.catalogo.model.dto;


import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResponse {

    private String id;
    private String name;
    private Double price;
    @JsonProperty("general_description")
    private String generalDescription;
    private Map<String, String> specifications;
    private List<String> tags;

    public ProductResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
