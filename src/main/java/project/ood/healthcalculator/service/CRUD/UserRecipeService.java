package project.ood.healthcalculator.service.CRUD;

import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserRecipe;

import java.util.List;

public interface UserRecipeService {
    List<UserRecipe> retrieveAll(User user);
    List<UserRecipe> retrieveAll(boolean shared);
}
