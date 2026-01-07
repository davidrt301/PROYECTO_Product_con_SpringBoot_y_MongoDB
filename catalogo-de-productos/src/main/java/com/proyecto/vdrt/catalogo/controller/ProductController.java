package com.proyecto.vdrt.catalogo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.vdrt.catalogo.model.dto.ProductRequest;
import com.proyecto.vdrt.catalogo.model.dto.ProductResponse;
import com.proyecto.vdrt.catalogo.service.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




/**
 * Controlador REST para la gestión de productos.
 * Expone endpoints para operaciones CRUD y búsquedas avanzadas.
 */
@RestController
@RequestMapping("/products")
@Tag(name = "Gestión de Productos", description = "Operaciones relacionadas con la gestión de productos en el catálogo.")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    /**
     * Obtiene el listado completo de todos los productos disponibles.
     * @return Lista de objetos ProductResponse.
     */
    @GetMapping
    @Operation(summary = "Obtiene todos los productos",
            description = "Retorna una lista de todos los productos disponibles en el catálogo.")
    public List<ProductResponse> getAllProducts() {
        return productService.getAll();
    }

    /**
     * Obtiene un producto específico por su ID.
     * @param id Identificador único del producto.
     * @return El objeto ProductResponse correspondiente al ID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un producto por ID",
            description = "Retorna un producto específico buscando por su identificador único.")

    public ProductResponse getById(@PathVariable String id) {
        return productService.getById(id);
    }

    /**
     * Busca productos que contengan una palabra clave en su nombre o descripción.
     * @param word Palabra clave para la búsqueda.
     * @return Lista de productos que coinciden con la búsqueda.
     */
    @GetMapping("/search")
    @Operation(summary = "Busca productos por palabra clave",
            description = "Retorna una lista de productos cuyo nombre o descripción contengan la palabra clave especificada.")
    public List<ProductResponse> getPerWord(@RequestParam(name = "palabra") String word) {
        return productService.getPerWord(word);
    }

    /**
     * Crea un nuevo producto en el catálogo.
     * @param product Objeto ProductRequest con los datos del nuevo producto.
     * @return El producto creado con su ID asignado.
     */
    @PostMapping
    @Operation(summary = "Crea un nuevo producto",
            description = "Crea un nuevo producto en el catálogo y lo retorna con su ID asignado.")
    public ProductResponse save(@RequestBody @Valid ProductRequest product) {
        return productService.save(product);
    }

    /**
     * Actualiza la información de un producto existente.
     * @param id Identificador del producto a actualizar.
     * @param product Objeto ProductRequest con los nuevos datos.
     * @return El producto actualizado.
     */
    @PutMapping("/update/{id}")
    @Operation(summary = "Actualiza un producto existente",
            description = "Actualiza la información de un producto existente buscando por su ID.")
    public ProductResponse updateById(@PathVariable String id, @Valid @RequestBody ProductRequest product) {
        return productService.updateById(id, product);
    }

    /**
     * Busca productos que tengan una etiqueta (tag) específica.
     * @param tag Etiqueta a buscar.
     * @return Lista de productos asociados a esa etiqueta.
     */
    @GetMapping("/tag")
    @Operation(summary = "Busca productos por etiqueta",
            description = "Retorna una lista de productos que tienen una etiqueta (tag) específica.")
    public List<ProductResponse> getByTag(@RequestParam String tag) {
        return productService.getByTag(tag);
    }
    
    /**
     * Busca productos por una característica específica en sus especificaciones técnicas.
     * Ejemplo: ?llave=RAM&valor=16GB
     * 
     * @param key La clave de la especificación (ej. "Color", "Memoria").
     * @param value El valor a buscar (ej. "Rojo", "16GB").
     * @return Lista de productos que coinciden.
     */
    @GetMapping("/specification")
    @Operation(summary = "Busca productos por especificación técnica",
            description = "Retorna una lista de productos que tienen una especificación técnica con una clave y un valor específicos.")
    public List<ProductResponse> getBySpecification (@RequestParam(name = "llave") String key, @RequestParam(name = "valor") String value){
        return productService.getBySpecification(key, value);
    }
        
    /**
     * Filtra productos por un rango de precios.
     * 
     * @param min Precio mínimo (mayor o igual).
     * @param max Precio máximo (menor o igual).
     * @return Lista de productos dentro del rango.
     */
    @GetMapping("/price")
    @Operation(summary = "Filtra productos por rango de precios",
            description = "Retorna una lista de productos cuyo precio se encuentra dentro de un rango especificado.")
    public List<ProductResponse> getByPriceRange(@RequestParam(name = "mallorigal") Double min, @RequestParam(name = "menorigual") Double max){
        return productService.getByPriceRange(min, max);
    }
    
    
    

    /**
     * Elimina un producto del catálogo por su ID.
     * @param id Identificador del producto a eliminar.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un producto por ID",
            description = "Elimina un producto del catálogo buscando por su identificador único.")
    public void deleteProduct(@PathVariable String id){
        productService.delete(id);
    }
    
    
    

}
