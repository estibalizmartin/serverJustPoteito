package ingredients.repository;

import ingredients.model.Ingredients;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientsRepository extends CrudRepository<Ingredients, Integer> {

}
