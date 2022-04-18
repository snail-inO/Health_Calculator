package project.ood.healthcalculator.service;

import org.springframework.data.repository.CrudRepository;
import project.ood.healthcalculator.dao.FoodDAO;
import project.ood.healthcalculator.dao.FoodNutrientsDAO;
import project.ood.healthcalculator.dao.NutrientDAO;
import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.entity.FoodNutrients;
import project.ood.healthcalculator.entity.Nutrient;

import java.util.List;
import java.util.stream.Collectors;

public class SaveDataServiceImpl implements SaveDataService{
    private FoodDAO foodDAO;
    private NutrientDAO nutrientDAO;
    private FoodNutrientsDAO foodNutrientsDAO;

    public SaveDataServiceImpl(FoodDAO foodDAO, NutrientDAO nutrientDAO, FoodNutrientsDAO foodNutrientsDAO) {
        this.foodDAO = foodDAO;
        this.nutrientDAO = nutrientDAO;
        this.foodNutrientsDAO = foodNutrientsDAO;
    }

    @Override
    public void saveFood(Food food) {
        Thread thread = new Thread(() -> {
            List<FoodNutrients> foodNutrients = food.getFoodNutrients();
            List<Nutrient> nutrients = foodNutrients.stream().map(FoodNutrients::getNutrient).collect(Collectors.toList());

            foodDAO.save(new Food(food));
            nutrientDAO.saveAll(nutrients);
            foodNutrientsDAO.saveAll(foodNutrients);
        });

        thread.start();
    }
}