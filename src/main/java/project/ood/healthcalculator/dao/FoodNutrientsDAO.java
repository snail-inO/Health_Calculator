package project.ood.healthcalculator.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.entity.FoodNutrients;

import java.util.List;


public interface FoodNutrientsDAO extends CrudRepository<FoodNutrients, Long> {

}
