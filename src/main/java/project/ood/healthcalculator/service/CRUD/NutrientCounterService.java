package project.ood.healthcalculator.service.CRUD;

import project.ood.healthcalculator.entity.NutrientCounter;
import project.ood.healthcalculator.entity.UserRecipe;

import java.util.List;

public interface NutrientCounterService {
    List<NutrientCounter> retrieveAll(UserRecipe userRecipe);
}
