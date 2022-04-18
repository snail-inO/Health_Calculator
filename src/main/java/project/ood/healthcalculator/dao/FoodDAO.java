package project.ood.healthcalculator.dao;

import org.springframework.data.repository.CrudRepository;
import project.ood.healthcalculator.entity.Food;

import java.util.List;

public interface FoodDAO extends CrudRepository<Food,Long> {
    List<Food> findByDescription(String name);
    Food findByFdcId(long id);
}
