package project.ood.healthcalculator.service;

import project.ood.healthcalculator.entity.Food;

import java.util.List;

public interface FoodSearchService {
    Food byId(long id);
    List<Food> byName(String name, int pageNum);
}
