package project.ood.healthcalculator.service;

import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.entity.UserFoods;

import java.util.List;

public interface UserFoodService {
    List<UserFoods> retrieveAll();
}
