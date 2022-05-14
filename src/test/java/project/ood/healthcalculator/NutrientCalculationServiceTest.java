package project.ood.healthcalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.ood.healthcalculator.entity.*;
import project.ood.healthcalculator.enums.SpecialIdEnum;
import project.ood.healthcalculator.service.FoodSearchServiceImpl;
import project.ood.healthcalculator.service.NutrientCalculationServiceImpl;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class NutrientCalculationServiceTest {
    private final NutrientCalculationServiceImpl nutrientCalculationService;

    private final UserFoods userFoods;

    @Autowired
    public NutrientCalculationServiceTest(FoodSearchServiceImpl foodSearchService) {
        this.nutrientCalculationService = new NutrientCalculationServiceImpl();
        List<Food> foods = foodSearchService.byName("apple", 1);
        userFoods = UserFoods.newBuilder().withFood(foods.get(0))
                .withUserFoodsId(SpecialIdEnum.NOT_USED.getId()).withUser(User.newBuilder().withUid(2).build())
                .withValue(100).build();
    }

    @Test
    public void calculateFoodNutrient() {
        Map<Long, NutrientCounter> res = nutrientCalculationService.calculateNutrition(userFoods);

        Food food = userFoods.getFood();
        FoodNutrients foodNutrients = food.getFoodNutrients().get(0);
        float value = userFoods.getValue();
        float servingSize = food.getServingSize();
        float nutrientValue = foodNutrients.getValue();
        long nutrientId = foodNutrients.getNutrient().getNutrientId();
        Assertions.assertNotNull(res);
        Assertions.assertFalse(res.isEmpty());
        Assertions.assertEquals(value / servingSize * nutrientValue, res.get(nutrientId).getValue());
    }
}
