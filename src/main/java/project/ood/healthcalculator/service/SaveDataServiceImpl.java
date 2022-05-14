package project.ood.healthcalculator.service;

import org.springframework.stereotype.Service;
import project.ood.healthcalculator.dao.FoodDAO;
import project.ood.healthcalculator.dao.FoodNutrientsDAO;
import project.ood.healthcalculator.dao.NutrientDAO;
import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.entity.FoodNutrients;
import project.ood.healthcalculator.entity.Nutrient;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Service
public class SaveDataServiceImpl implements SaveDataService{
    private final FoodDAO foodDAO;
    private final NutrientDAO nutrientDAO;
    private final FoodNutrientsDAO foodNutrientsDAO;
    static Lock lock= new ReentrantLock();

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
            food.setFoodNutrients(null);
            foodDAO.save(food);
            lock.lock();
            nutrientDAO.saveAll(nutrients);
            lock.unlock();
            foodNutrientsDAO.saveAll(foodNutrients);
        });

        thread.start();
    }
}
