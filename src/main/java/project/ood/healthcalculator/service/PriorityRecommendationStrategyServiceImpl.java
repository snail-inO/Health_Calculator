package project.ood.healthcalculator.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import project.ood.healthcalculator.entity.EntityInterface;
import project.ood.healthcalculator.entity.NutrientCounter;
import project.ood.healthcalculator.entity.UserMeal;
import project.ood.healthcalculator.entity.UserRecipe;
import project.ood.healthcalculator.enums.EntityTypeEnum;
import project.ood.healthcalculator.enums.FirstPriorityNutrientEnum;
import project.ood.healthcalculator.enums.NutrientPriority;
import project.ood.healthcalculator.enums.SecondPriorityNutrientEnum;
import project.ood.healthcalculator.service.CRUD.CRUDService;
import project.ood.healthcalculator.service.CRUD.UserMealService;
import project.ood.healthcalculator.service.CRUD.UserMealServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PriorityRecommendationStrategyServiceImpl implements RecommendationStrategyService {
    private final UserMealService service;

    public PriorityRecommendationStrategyServiceImpl(UserMealServiceImpl userMealService) {
        service = userMealService;
    }

    @Override
    public Map<Long, Float> calculateDifference(UserRecipe current, List<NutrientCounter> target) {
        Map<Long, Float> curMap = current.getNutrientCounters().stream()
                .collect(Collectors.toMap(counter -> counter.getNutrient().getNutrientId(), NutrientCounter::getValue));
        Map<Long, Float> dif = new HashMap<>();

        target.forEach(counter -> {
            long nutrientId = counter.getNutrient().getNutrientId();
            float sub = curMap.containsKey(nutrientId) ? curMap.get(nutrientId) : 0;
                dif.put(nutrientId, counter.getValue() - sub);
        });

        return dif;
    }

    @Override
    public UserMeal findMatch(Map<Long, Float> difference) {
        List<UserMeal> list = service.retrieveAll(true);
        float min1 = -1;
        float min2 = -1;
        UserMeal re = null;
        for (UserMeal userMeal : list) {
            Map<Long, Float> curMap = userMeal.getNutrientCounters().stream()
                    .collect(Collectors.toMap(counter -> counter.getNutrient().getNutrientId(), NutrientCounter::getValue));
            float res1 = compareFirstPriority(curMap, difference);
            if ((min1 == -1) || (res1 >= 0 && res1 < min1)) {
                min1 = res1;
                min2 = compareSecondPriority(curMap, difference);
                re = userMeal;
            } else if (res1 >= 0 && res1 == min1) {
                float res2 = compareSecondPriority(curMap, difference);
                if (res2 >= 0 && res2 < min2) {
                    min2 = res2;
                    re = userMeal;
                }
            }
        }

        return re;
    }

    private float compareFirstPriority(Map<Long, Float> nutrient, Map<Long, Float> dif) {
        float sum = 0;
        for (FirstPriorityNutrientEnum e : FirstPriorityNutrientEnum.values()) {
            long id = e.getNutrientId();
            float value = nutrient.containsKey(id) ? nutrient.get(id) : 0;
            float sub = dif.get(id) - value;
            if (sub < 0)
                return -1;
            sum += sub;
        }

        return sum;
    }

    private float compareSecondPriority(Map<Long, Float> nutrient, Map<Long, Float> dif) {
        float sum = 0;
        for (SecondPriorityNutrientEnum e : SecondPriorityNutrientEnum.values()) {
            long id = e.getNutrientId();
            float value = nutrient.containsKey(id) ? nutrient.get(id) : 0;
            float sub = dif.get(id) - value;
            if (sub < 0)
                return -1;
            sum += sub;
        }

        return sum;
    }
}
