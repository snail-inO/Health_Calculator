package project.ood.healthcalculator.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;

import java.util.List;


public interface UserFoodsDAO extends CrudRepository<UserFoods, Long> {
    List<UserFoods> findByUser(User user);
}
