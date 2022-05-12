package project.ood.healthcalculator.dao;

import org.springframework.data.repository.CrudRepository;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserRecipe;

import java.util.List;

public interface UserRecipeDAO extends CrudRepository<UserRecipe, Long> {
    List<UserRecipe> findByUser(User user);
    List<UserRecipe> findByShared(boolean shared);
}
