package project.ood.healthcalculator.service;

import project.ood.healthcalculator.entity.*;

import java.util.Map;

public interface NutrientCalculationService {
    Map<Long, NutrientCounter> calculateNutrition(User user);
    Map<Long, NutrientCounter> calculateNutrition(UserRecipe userRecipe);
    Map<Long, NutrientCounter> calculateNutrition(UserMeal userMeal);
    Map<Long, NutrientCounter> calculateNutrition(UserFoods userFoods);
}
