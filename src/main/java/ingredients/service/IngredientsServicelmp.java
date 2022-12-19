package ingredients.service;

import ingredients.model.Ingredients;
import ingredients.model.IngredientsGetResponse;
import ingredients.repository.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IngredientsServicelmp implements IngredientsService{

    @Autowired
    IngredientsService ingredientsService;
    @Autowired
    IngredientsRepository ingredientsRepository;

    @Override
    public List<IngredientsGetResponse> getAllIngredients() {
        return null;
    }

    @Override
    public IngredientsGetResponse getIngredientsById() {
        return null;
    }

    @Override
    public int createIngredients(Ingredients ingredients) {
        return 0;
    }

    @Override
    public int updateIngredients(Ingredients ingredients) {
        return 0;
    }

    @Override
    public int deleteIngredientsById(long id) {
        return 0;
    }
}
