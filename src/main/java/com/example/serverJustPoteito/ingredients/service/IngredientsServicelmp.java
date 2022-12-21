package ingredients.service;

import ingredients.model.IngredientUpdateResponse;
import ingredients.model.Ingredients;
import ingredients.model.IngredientsPostRequest;
import ingredients.repository.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class IngredientsServicelmp implements IngredientsService{

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Override
    public Iterable<Ingredients> getAllIngredients() {
        return ingredientsRepository.findAll();
    }

    @Override
    public Ingredients getIngredientById(Integer id) {
        return ingredientsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NO_CONTENT, "This ingredient doesn't exists...")
                );
    }

    @Override
    public Ingredients createIngredient(IngredientsPostRequest ingredientsPostRequest) {
        Ingredients ingredients = new Ingredients(
                ingredientsPostRequest.getName(),
                ingredientsPostRequest.getType()
        );
        return ingredientsRepository.save(ingredients);
    }

    @Override
    public IngredientUpdateResponse updateIngredient(Integer id, IngredientsPostRequest ingredientsPostRequest) {
        boolean ingredientAlreadyExists = ingredientsRepository.existsById(id);

        Ingredients ingredients = new Ingredients(
                ingredientsPostRequest.getName(),
                ingredientsPostRequest.getType()
        );
        ingredients = ingredientsRepository.save(ingredients);

        return new IngredientUpdateResponse(ingredientAlreadyExists, ingredients);
    }

    @Override
    public void deleteIngredientsById(Integer id) {
        ingredientsRepository.deleteById(id);
    }
}