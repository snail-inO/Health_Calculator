package project.ood.healthcalculator.service;

import org.springframework.stereotype.Service;
import project.ood.healthcalculator.entity.NutrientCounter;
import project.ood.healthcalculator.entity.UserMeal;
import project.ood.healthcalculator.entity.UserRecipe;

import java.util.List;
import java.util.Map;

@Service
public class StrategyServiceImpl {
    private RecommendationStrategyService strategyService;

    public void setStrategyService(RecommendationStrategyService strategyService) {
        this.strategyService = strategyService;
    }

    public UserMeal applyStrategy(UserRecipe current, List<NutrientCounter> target) {
        Map<Long, Float> dif = strategyService.calculateDifference(current, target);
        return strategyService.findMatch(dif);
    }
}
