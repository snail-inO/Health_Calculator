package project.ood.healthcalculator.service;

import project.ood.healthcalculator.entity.NutrientCounter;
import project.ood.healthcalculator.entity.UserMeal;
import project.ood.healthcalculator.entity.UserRecipe;
import project.ood.healthcalculator.enums.EntityTypeEnum;

import java.util.List;
import java.util.Map;

public interface RecommendationStrategyService {
    Map<Long, Float> calculateDifference(UserRecipe current, List<NutrientCounter> target);
    UserMeal findMatch(Map<Long, Float> difference);
}
