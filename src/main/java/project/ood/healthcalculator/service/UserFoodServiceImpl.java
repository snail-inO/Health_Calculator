package project.ood.healthcalculator.service;

import project.ood.healthcalculator.dao.FoodDAO;
import project.ood.healthcalculator.dao.UserDAO;
import project.ood.healthcalculator.dao.UserFoodsDAO;
import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;
import project.ood.healthcalculator.enums.RestEnum;
import project.ood.healthcalculator.utils.CustomException;

import java.util.List;
import java.util.stream.Collectors;

public class UserFoodServiceImpl implements UserFoodService{
    private User user;
    private FoodDAO foodDAO;
    private UserFoodsDAO userFoodsDAO;
    private UserDAO userDAO;

    public UserFoodServiceImpl(User user, FoodDAO foodDAO, UserFoodsDAO userFoodsDAO, UserDAO userDAO) {
        this.user = user;
        this.foodDAO = foodDAO;
        this.userFoodsDAO = userFoodsDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<UserFoods> showFoods() {
        return userFoodsDAO.findByUser(user);
    }

    @Override
    public UserFoods addFood(long id, float value) {
        Food food = foodDAO.findByFdcId(id);
        if (food == null)
            throw new CustomException(RestEnum.NOT_FOUND);
        UserFoods newFood = new UserFoods(value, user, food);
        newFood = userFoodsDAO.save(newFood);
        user.setFoodCounts(user.getFoodCounts() + 1);
        userDAO.save(user);

        return newFood;
    }

    @Override
    public UserFoods removeFood(long id) {
        userFoodsDAO.deleteById(id);
        user.setFoodCounts(user.getFoodCounts() - 1);
        userDAO.save(user);

        return null;
    }

    @Override
    public UserFoods updateFood(long id, float value) {
        UserFoods userFoods = userFoodsDAO.findById(id);
        if (userFoods == null)
            throw new CustomException(RestEnum.NOT_FOUND);
        userFoods.setValue(value);
        userFoods = userFoodsDAO.save(userFoods);

        return userFoods;
    }
}
