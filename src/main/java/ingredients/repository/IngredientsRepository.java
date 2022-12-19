package ingredients.repository;

import ingredients.model.Ingredients;

import java.util.List;

public interface IngredientsRepository {
    List<Ingredients> getAllIngredients();
    Ingredients findById (long id);
    int create (Ingredients ingredients);
    int update (Ingredients ingredients);
    int deleteById (long id);
    //Ingredients findByDishId (long dishId);
}
