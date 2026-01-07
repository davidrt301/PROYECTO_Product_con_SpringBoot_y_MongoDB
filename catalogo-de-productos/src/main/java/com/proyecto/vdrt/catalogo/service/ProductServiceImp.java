package com.proyecto.vdrt.catalogo.service;


import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.proyecto.vdrt.catalogo.exception.ProductAlreadyExistException;
import com.proyecto.vdrt.catalogo.exception.ProductNotFoundException;
import com.proyecto.vdrt.catalogo.mapper.ProductMapper;
import com.proyecto.vdrt.catalogo.model.document.Product;
import com.proyecto.vdrt.catalogo.model.dto.ProductRequest;
import com.proyecto.vdrt.catalogo.model.dto.ProductResponse;
import com.proyecto.vdrt.catalogo.repository.ProductRepository;

@Service
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final MongoTemplate mongoTemplate;


    public ProductServiceImp(ProductRepository productRepository, ProductMapper productMapper, MongoTemplate mongoTemplate) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public ProductResponse save(ProductRequest product) {
        if (productRepository.existsByName(product.getName())) {
            throw new ProductAlreadyExistException();
        }
        Product p = productMapper.toDocument(product);

        p = productRepository.save(p);

        ProductResponse responce = productMapper.toResponse(p);

        return responce;
    }

    @Override
    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream()
            .map(productMapper::toResponse)//(product ->productMapper.toResponse(product))
            .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getById(String id) {
        // 1. El repositorio busca y devuelve una "caja" (Optional)
        Optional<Product> productOptional = productRepository.findById(id);

        // 2. Usamos programación funcional para procesar el contenido
        return productOptional
                .map(productMapper::toResponse) // Si existe, lo convertimos a DTO //(product -> productMapper.toResponse(product))
                .orElseThrow(() -> new ProductNotFoundException()); // Si está vacío, lanzamos error
    }

    @Override
    public List<ProductResponse> getPerWord(String word) {
        return productRepository.findByNameOrDescriptionRegex(word).stream()
            .map(productMapper::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse updateById(String id, ProductRequest product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado con ID: " + id));
        
        productMapper.updateProductFromRequest(product, existingProduct);

        return productMapper.toResponse(productRepository.save(existingProduct));
    }

    @Override
    public List<ProductResponse> getByTag(String tag) {
        return productRepository.findByTags(tag).stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getBySpecification(String key, String value) {
        // Construimos una query dinámica: specifications.KEY = VALUE
        // Usamos MongoTemplate porque el nombre del campo (key) es variable
        
        // Generamos un regex que permite espacios opcionales entre cada caracter
        // Ejemplo: "16GB" se convierte en "\Q1\E\s*\Q6\E\s*\QG\E\s*\QB\E\s*"
        String regexValue = value.replace(" ", "").chars()
                .mapToObj(c -> Pattern.quote(String.valueOf((char) c)) + "\\s*")
                .collect(Collectors.joining());

        Query query = new Query(Criteria.where("specifications." + key.toLowerCase().replace(" ", "")).regex(regexValue, "i"));
        
        return mongoTemplate.find(query, Product.class).stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    /*
    Cuando MongoDB ejecuta la consulta findByPriceBetween(min, max), 
    busca productos cuyo precio sea: Precio >= 1500 Y Precio <= 700.  
    */
    @Override
    public List<ProductResponse> getByPriceRange(Double min, Double max) {
        // Si el usuario envía el mínimo mayor que el máximo, los intercambiamos
        if (min.compareTo(max) > 0) {
            Double temp = min;
            min = max;
            max = temp;
        }
        
        return productRepository.findByPriceBetween(min, max).stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

}
