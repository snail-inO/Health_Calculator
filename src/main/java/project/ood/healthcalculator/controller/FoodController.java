package project.ood.healthcalculator.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.ood.healthcalculator.dao.FoodDAO;
import project.ood.healthcalculator.dao.FoodNutrientsDAO;
import project.ood.healthcalculator.dao.NutrientDAO;
import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.service.FoodSearchServiceImpl;
import project.ood.healthcalculator.service.SaveDataServiceImpl;
import project.ood.healthcalculator.utils.RestJSON;

import java.util.List;

@RestController
@RequestMapping("/food")
@Api(value = "Food Search APIs")
public class FoodController {
    @Autowired
    private FoodDAO foodDAO;
    @Autowired
    private NutrientDAO nutrientDAO;
    @Autowired
    private FoodNutrientsDAO foodNutrientsDAO;
    private FoodSearchServiceImpl foodSearchService;

    @GetMapping("/by_id")
    @ApiOperation(value = "Search food data by fdcId", notes = "Search for specific food")
    @ApiImplicitParam(name = "id", value = "Food FdcId")
    public Food searchFood(@RequestParam long id) {
        initialize();
        return foodSearchService.byId(id);
    }

    @GetMapping("/by_name")
    @ApiOperation(value = "Search food data by name", notes = "Search for certain kind of food")
    public RestJSON searchFoods(@RequestParam String name, @RequestParam(required = false, defaultValue = "1") int pageNum) throws InterruptedException{
        initialize();

        List<Food> list = foodSearchService.byName(name, pageNum);
        RestJSON res = new RestJSON(list);
        SaveDataServiceImpl saveDataService = new SaveDataServiceImpl(foodDAO, nutrientDAO, foodNutrientsDAO);
        list.forEach(saveDataService::saveFood);
        return res;
    }

    private void initialize() {
        if (foodSearchService == null)
            foodSearchService = new FoodSearchServiceImpl(foodDAO);
    }
}
