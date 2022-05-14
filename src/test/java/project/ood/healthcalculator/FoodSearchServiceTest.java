package project.ood.healthcalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.service.FoodSearchServiceImpl;

import java.util.List;

@SpringBootTest
public class FoodSearchServiceTest {
    @Autowired
    private FoodSearchServiceImpl foodSearchService;

    @Test
    public void searchByName() {
        String name = "fish";
        List<Food> foods = foodSearchService.byName(name, 1);

        Assertions.assertFalse(foods.isEmpty());
        Assertions.assertTrue(foods.get(0).getDescription().toLowerCase().contains(name));
        Assertions.assertFalse(foods.get(0).getFoodNutrients().isEmpty());
        Assertions.assertNotNull(foods.get(0).getFoodNutrients().get(0).getNutrient());
    }

    @Test
    public void searchById() {
        long fdcId = 454004;
        Food food =foodSearchService.byId(fdcId);

        Assertions.assertNotNull(food);
        Assertions.assertEquals(fdcId, food.getFdcId());
        Assertions.assertNotNull(food.getFoodNutrients());
    }
}
