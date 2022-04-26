package project.ood.healthcalculator.service;

import project.ood.healthcalculator.dao.UserFoodsDAO;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;
import project.ood.healthcalculator.service.factory.CRUDServiceImpl;

import java.util.List;

public class UserFoodServiceImpl extends CRUDServiceImpl<UserFoods, UserFoodsDAO> implements UserFoodService {
    private final User user;

    public UserFoodServiceImpl(UserFoodsDAO userFoodsDAO, User user) {
        super(userFoodsDAO);
        this.user = user;
    }


    public List<UserFoods> retrieveAll() {
        return super.getBaseDAO().findByUser(user);
    }
}
