package project.ood.healthcalculator.service;

import lombok.extern.slf4j.Slf4j;
import project.ood.healthcalculator.entity.*;
import project.ood.healthcalculator.enums.SpecialIdEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class NutrientCalculationServiceImpl implements NutrientCalculationService {
    private UserRecipe userRecipe;
    private UserMeal userMeal;

    public Map<Long, NutrientCounter> calculateNutrition(User user) {
        Map<Long, NutrientCounter> nutritionAccumulator = new HashMap<>();
        List<UserRecipe> userRecipes = user.getUserRecipes();

        for (UserRecipe userRecipe : userRecipes) {
            nutritionAccumulator.putAll(calculateNutrition(userRecipe));
        }

        return nutritionAccumulator;
    }
    @Override
    public Map<Long, NutrientCounter> calculateNutrition(UserRecipe userRecipe) {
        Map<Long, NutrientCounter> nutritionAccumulator = new HashMap<>();
        this.userRecipe = userRecipe;
        List<UserMeal> userMeals = userRecipe.getUserMeals();

        for (UserMeal userMeal : userMeals) {
            nutritionAccumulator.putAll(calculateNutrition(userMeal));
        }

        return nutritionAccumulator;
    }

    @Override
    public Map<Long, NutrientCounter> calculateNutrition(UserMeal userMeal) {
        Map<Long, NutrientCounter> nutritionAccumulator = new HashMap<>();
        this.userMeal = userMeal;
        List<UserFoods> userFoods = userMeal.getUserFoods();

        for (UserFoods userFood : userFoods) {
            nutritionAccumulator.putAll(calculateNutrition(userFood));
        }

        return nutritionAccumulator;
    }

    @Override
    public Map<Long, NutrientCounter> calculateNutrition(UserFoods userFoods) {
        Map<Long, NutrientCounter> nutritionAccumulator = new HashMap<>();
        User user = userFoods.getUser();

        Food food = userFoods.getFood();
        float calculatedSize = userFoods.getValue() / food.getServingSize();
        food.getFoodNutrients().forEach(foodNutrient -> {
            float unitValue = foodNutrient.getValue();
            long nutrientId = foodNutrient.getNutrient().getNutrientId();

            if (nutritionAccumulator.containsKey(nutrientId)) {
                NutrientCounter nutrientCounter = nutritionAccumulator.get(nutrientId);
                nutritionAccumulator.replace(nutrientId, NutrientCounter.newBuilder()
                        .withNutrientCounter(nutrientCounter)
                        .withValue(nutrientCounter.getValue() + unitValue * calculatedSize).build());
            } else
                nutritionAccumulator.put(nutrientId, NutrientCounter.newBuilder()
                        .withNutrientCounterId(SpecialIdEnum.NOT_USED.getId()).withValue(unitValue * calculatedSize)
                        .withUser(user)
                        .withUserRecipe(userRecipe).withUserMeal(userMeal).withUserFoods(userFoods)
                        .withNutrient(Nutrient.newBuilder().withNutrientId(nutrientId).build()).build());
        });

        return nutritionAccumulator;
    }
}
