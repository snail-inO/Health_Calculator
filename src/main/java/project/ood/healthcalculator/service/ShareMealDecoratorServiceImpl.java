package project.ood.healthcalculator.service;

import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;
import project.ood.healthcalculator.entity.UserMeal;
import project.ood.healthcalculator.enums.SpecialIdEnum;
import project.ood.healthcalculator.service.CRUD.CRUDService;

import java.util.ArrayList;
import java.util.List;

public class ShareMealDecoratorServiceImpl extends BaseDecoratorServiceImpl {
    private CRUDService<UserMeal> userMealService;

    public ShareMealDecoratorServiceImpl(CRUDService<UserFoods> wrappee, CRUDService<UserMeal> userMealService) {
        super(wrappee);
        this.userMealService = userMealService;
    }

    @Override
    public UserMeal create(Object o) {
        List<UserFoods> userFoods = new ArrayList<>();
        User user = ((UserMeal) o).getUser();
        ((UserMeal) o).getUserFoods().forEach(userFood -> {
            userFood.setUser(user);
            userFoods.add((UserFoods) super.create(userFood));
        });
        return userMealService.create(UserMeal.newBuilder().withUserMeal((UserMeal) o).withShared(false)
                .withUserMealId(SpecialIdEnum.NOT_USED.getId()).withUserFoods(userFoods).build());
    }

}
