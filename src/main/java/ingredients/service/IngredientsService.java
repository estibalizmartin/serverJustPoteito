package ingredients.service;

import ingredients.model.Ingredients;
import ingredients.model.IngredientsGetResponse;

import java.util.List;

public interface IngredientsService {
    List<IngredientsGetResponse> getAllIngredients();
    IngredientsGetResponse getIngredientsById();
    int createIngredients(Ingredients ingredients);
    int updateIngredients(Ingredients ingredients);
    int deleteIngredientsById(long id);

    //TODO BUSCAR POR ID DE PLATO
}
