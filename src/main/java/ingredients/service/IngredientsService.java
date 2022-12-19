package ingredients.service;

import ingredients.model.IngredientUpdateResponse;
import ingredients.model.Ingredients;
import ingredients.model.IngredientsPostRequest;

public interface IngredientsService {
    Iterable<Ingredients> getAllIngredients();
    Ingredients getIngredientById(Integer id);
    Ingredients createIngredient(IngredientsPostRequest ingredientsPostRequest);
    IngredientUpdateResponse updateIngredient(Integer id, IngredientsPostRequest ingredientsPostRequest);
    void deleteIngredientsById(Integer id);

    //TODO BUSCAR POR ID DE PLATO
    /*Ingredientes por id de plato =
        tabla PlatoIngredientes
        Integer idPlato
        Integer idIngredientes
        Float? cantidad
     */
}
