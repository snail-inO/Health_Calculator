package project.ood.healthcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.ood.healthcalculator.dao.FoodDAO;
import project.ood.healthcalculator.dao.FoodNutrientsDAO;
import project.ood.healthcalculator.dao.NutrientDAO;
import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.service.FoodSearchServiceImpl;
import project.ood.healthcalculator.service.SaveDataServiceImpl;

import java.util.List;

@RestController
public class FoodController {
    @Autowired
    private FoodDAO foodDAO;
    @Autowired
    private NutrientDAO nutrientDAO;
    @Autowired
    private FoodNutrientsDAO foodNutrientsDAO;
    private FoodSearchServiceImpl foodSearchService;

    @GetMapping("/food/by_id")
    public Food searchFood(@RequestParam long id) {
        initialize();
        return foodSearchService.byId(id);
    }

    @GetMapping("/food/by_name")
    public List<Food> searchFoods(@RequestParam String name, @RequestParam(required = false, defaultValue = "1") int pageNum) throws InterruptedException{
        initialize();

        List<Food> list = foodSearchService.byName(name, pageNum);
        SaveDataServiceImpl saveDataService = new SaveDataServiceImpl(foodDAO, nutrientDAO, foodNutrientsDAO);
        list.forEach(saveDataService::saveFood);
        return list;
    }

    private void initialize() {
        if (foodSearchService == null)
            foodSearchService = new FoodSearchServiceImpl(foodDAO);
    }
}
