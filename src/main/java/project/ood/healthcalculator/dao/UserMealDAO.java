package project.ood.healthcalculator.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserMeal;

import java.util.List;


public interface UserMealDAO extends CrudRepository<UserMeal, Long> {
    List<UserMeal> findByUser(User user);
    List<UserMeal> findByShared(boolean shared);
}
