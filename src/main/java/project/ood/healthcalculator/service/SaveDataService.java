package project.ood.healthcalculator.service;

import org.springframework.data.repository.CrudRepository;
import project.ood.healthcalculator.dao.FoodDAO;
import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.entity.FoodNutrients;
import project.ood.healthcalculator.entity.Nutrient;

import java.util.List;

public interface SaveDataService {
    void saveFood(Food food);
}
