package com.proyecto.vdrt.catalogo.mapper;

import org.springframework.stereotype.Component;

import com.proyecto.vdrt.catalogo.model.document.Product;
import com.proyecto.vdrt.catalogo.model.dto.ProductRequest;
import com.proyecto.vdrt.catalogo.model.dto.ProductResponse;

@Component
public class ProductMapper {

    //Convierte un objeto de solicitud (DTO) a una entidad de documento Product.
    public Product toDocument(ProductRequest p){
        Product responce = new Product();
        responce.setName(p.getName());
        responce.setPrice(p.getPrice());
        responce.setGeneralDescription(p.getGeneralDescription());
        responce.setSpecifications(p.getSpecifications());
        responce.setTags(p.getTags());

        return responce;
    }

    //Convierte una entidad de documento Product a un objeto de respuesta (DTO).
    public ProductResponse toResponse (Product r){
        ProductResponse response = new ProductResponse();

        response.setId(r.getId());
        response.setName(r.getName());
        response.setPrice(r.getPrice());
        response.setGeneralDescription(r.getGeneralDescription());
        response.setSpecifications(r.getSpecifications());
        response.setTags(r.getTags());

        return response;
    }

    // Actualiza una entidad existente con los datos del DTO, sin perder el ID ni otros datos no mapeados.
    public void updateProductFromRequest(ProductRequest request, Product product) {
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setGeneralDescription(request.getGeneralDescription());
        product.setSpecifications(request.getSpecifications());
        product.setTags(request.getTags());
    }
}
