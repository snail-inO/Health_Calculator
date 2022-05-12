package project.ood.healthcalculator.service.CRUD;

import org.springframework.stereotype.Component;
import project.ood.healthcalculator.dao.UserRecipeDAO;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserRecipe;

import java.util.List;

@Component
public class UserRecipeServiceImpl extends CRUDServiceImpl<UserRecipe, UserRecipeDAO> implements  UserRecipeService{

    public UserRecipeServiceImpl(UserRecipeDAO userRecipeDAO) {
        super(userRecipeDAO);
    }

    @Override
    public List<UserRecipe> retrieveAll(User user) {
        return getBaseDAO().findByUser(user);
    }

    @Override
    public List<UserRecipe> retrieveAll(boolean shared) {
        return getBaseDAO().findByShared(shared);
    }
}
