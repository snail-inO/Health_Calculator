package project.ood.healthcalculator.dao;

import org.springframework.data.repository.CrudRepository;
import project.ood.healthcalculator.entity.FoodNutrients;


public interface FoodNutrientsDAO extends CrudRepository<FoodNutrients, Long> {

}
