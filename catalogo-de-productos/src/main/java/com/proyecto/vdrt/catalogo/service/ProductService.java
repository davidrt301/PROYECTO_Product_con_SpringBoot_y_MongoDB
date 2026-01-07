package com.proyecto.vdrt.catalogo.service;


import java.util.List;

import com.proyecto.vdrt.catalogo.model.dto.ProductRequest;
import com.proyecto.vdrt.catalogo.model.dto.ProductResponse;

/**
 * Interfaz que define la lógica de negocio para la gestión del catálogo de productos.
 */
public interface ProductService {

    /**
     * Guarda un nuevo producto en la base de datos.
     * @param product DTO con la información del producto a crear.
     * @return El producto creado con su ID asignado.
     */
    ProductResponse save (ProductRequest product);

    /**
     * Obtiene el listado completo de productos.
     * @return Lista de todos los productos disponibles.
     */
    List<ProductResponse> getAll ();
    
    /**
     * Busca un producto por su identificador único.
     * @param id Identificador del producto.
     * @return El producto encontrado.
     */
    ProductResponse getById (String id);
    
    /**
     * Busca productos que coincidan con una palabra clave en su nombre o descripción.
     * @param word Palabra clave a buscar.
     * @return Lista de productos coincidentes.
     */
    List<ProductResponse> getPerWord (String word);
    
    void delete (String id);
    
    ProductResponse updateById (String id, ProductRequest product);

    /**
     * Encuentra todos los productos que contengan una etiqueta específica.
     * @param tag Etiqueta a buscar (ej. "tecnología").
     * @return Lista de productos que contienen el tag.
     */
    List<ProductResponse> getByTag (String tag);

    /**
     * Busca productos por una especificación técnica dinámica (clave-valor).
     * @param key Nombre de la especificación (ej. "RAM", "Color").
     * @param value Valor de la especificación (ej. "16GB", "Rojo").
     * @return Lista de productos que cumplen con la especificación.
     */
    List<ProductResponse> getBySpecification (String key, String value);
    
    /**
     * Filtra productos cuyo precio se encuentre dentro de un rango inclusivo.
     * @param min Precio mínimo.
     * @param max Precio máximo.
     * @return Lista de productos en el rango de precios.
     */
    List<ProductResponse> getByPriceRange (Double min,  Double max);
}
