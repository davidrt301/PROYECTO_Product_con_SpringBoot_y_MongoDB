package com.proyecto.vdrt.catalogo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.proyecto.vdrt.catalogo.model.document.Product;

public interface ProductRepository extends MongoRepository<Product,String>{

    /**
     * Busca productos que contengan el texto proporcionado en su nombre o descripción general.
     * 
     * Explicación de la Query de MongoDB:
     * - $or: Busca documentos que cumplan al menos una de las condiciones de la lista.
     * - name / generalDescription: Campos donde se realiza la búsqueda.
     * - $regex: ?0: Aplica una expresión regular usando el primer parámetro (text).
     * - $options: 'i': Hace que la búsqueda sea insensible a mayúsculas/minúsculas (Case-Insensitive).
     * 
     * @param text Texto a buscar dentro del nombre o descripción.
     * @return Lista de productos que coinciden con el criterio.
     */
    @Query("""
            { '$or':
                [ { 'name': 
                    { '$regex': ?0, '$options': 'i' } 
                },
                    { 'generalDescription': { '$regex': ?0, '$options': 'i' } 
                    } 
                ] }
            """)
    List<Product> findByNameOrDescriptionRegex(String text);

    /**
     * Busca productos que contengan el tag especificado en su lista de tags.
     * Spring Data genera automáticamente la query: { 'tags' : tag }
     * Al ser 'tags' un array, MongoDB busca si el valor existe dentro del array.
     */
    List<Product> findByTags(String tag);

    /**
     * Busca productos cuyo precio esté dentro del rango especificado (inclusive).
     * Usamos @Query para asegurar que sea INCLUSIVO ($gte y $lte)
     */
    @Query("{ 'price' : { $gte: ?0, $lte: ?1 } }")
    List<Product> findByPriceBetween(Double min, Double max);

    boolean existsByName(String name);
}
