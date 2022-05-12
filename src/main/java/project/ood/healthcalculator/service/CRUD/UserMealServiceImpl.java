package project.ood.healthcalculator.service.CRUD;

import org.springframework.stereotype.Component;
import project.ood.healthcalculator.dao.UserMealDAO;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserMeal;

import java.util.List;

@Component
public class UserMealServiceImpl extends CRUDServiceImpl<UserMeal, UserMealDAO> implements UserMealService {

    public UserMealServiceImpl(UserMealDAO userMealDAO) {
        super(userMealDAO);
    }

    @Override
    public List<UserMeal> retrieveAll(User user) {
        return getBaseDAO().findByUser(user);
    }

    @Override
    public List<UserMeal> retrieveAll(boolean shared) {
        return getBaseDAO().findByShared(shared);
    }

}
