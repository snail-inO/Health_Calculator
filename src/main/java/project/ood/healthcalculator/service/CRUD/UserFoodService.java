package project.ood.healthcalculator.service.CRUD;

import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;

import java.util.List;

public interface UserFoodService {
    List<UserFoods> retrieveAll(User user);
}
