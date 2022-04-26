package project.ood.healthcalculator.service;

import project.ood.healthcalculator.dao.UserMealDAO;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserMeal;
import project.ood.healthcalculator.service.factory.CRUDServiceImpl;

import java.util.List;

public class UserMealServiceImpl extends CRUDServiceImpl<UserMeal, UserMealDAO> implements UserMealService {
    private User user;

    public UserMealServiceImpl(UserMealDAO userMealDAO, User user) {
        super(userMealDAO);
        this.user = user;
    }

    @Override
    public List<UserMeal> retrieveAll() {
        return super.getBaseDAO().findByUser(user);
    }

}
