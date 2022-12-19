package ingredients.repository;

import ingredients.model.Ingredients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcIngredientsRepository implements IngredientsRepository{

    //TODO CREAR BASE DE DATOS CON LOS NOMBRES EN INGLES
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Ingredients> getAllIngredients() {
        return jdbcTemplate.query("SELECT * FROM ingredients", BeanPropertyRowMapper.newInstance(Ingredients.class));
    }

    @Override
    public Ingredients findById(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ingredients WHERE id = ?", BeanPropertyRowMapper.newInstance(Ingredients.class), id);
    }

    @Override
    public int create(Ingredients ingredients) {
        return jdbcTemplate.update("INSERT INTO ingredients (name, type) VALUES (?, ?)",
                new Object[]{ingredients.getName(), ingredients.getType()});
    }

    @Override
    public int update(Ingredients ingredients) {
        return jdbcTemplate.update("UPDATE ingredients SET name = ?, type = ? WHERE id = ?",
                new Object[]{ingredients.getName(), ingredients.getType()});
    }

    @Override
    public int deleteById(long id) {
        return jdbcTemplate.update("DELETE FROM ingredients WHERE id = ?", id);
    }

    //TODO BUSCAR POR ID DE PLATO (AÑADIR VARIABLE EN INGREDIENTES)
}
