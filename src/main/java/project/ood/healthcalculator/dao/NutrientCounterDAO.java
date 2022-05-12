package project.ood.healthcalculator.dao;

import org.springframework.data.repository.CrudRepository;
import project.ood.healthcalculator.entity.NutrientCounter;
import project.ood.healthcalculator.entity.UserRecipe;

import java.util.List;

public interface NutrientCounterDAO extends CrudRepository<NutrientCounter, Long> {
    List<NutrientCounter> findByUserRecipe(UserRecipe userRecipe);
}
