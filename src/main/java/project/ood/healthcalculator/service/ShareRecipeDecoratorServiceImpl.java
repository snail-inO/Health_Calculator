package project.ood.healthcalculator.service;

import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserMeal;
import project.ood.healthcalculator.entity.UserRecipe;
import project.ood.healthcalculator.enums.SpecialIdEnum;
import project.ood.healthcalculator.service.CRUD.CRUDService;

import java.util.ArrayList;
import java.util.List;

public class ShareRecipeDecoratorServiceImpl extends BaseDecoratorServiceImpl {
    private CRUDService<UserRecipe> userRecipeService;

    public ShareRecipeDecoratorServiceImpl(CRUDService wrappee, CRUDService<UserRecipe> userRecipeService) {
        super(wrappee);
        this.userRecipeService = userRecipeService;
    }

    @Override
    public UserRecipe create(Object o) {
        List<UserMeal> userMeals = new ArrayList<>();
        User user = ((UserRecipe) o).getUser();
        ((UserRecipe) o).getUserMeals().forEach(userMeal -> {
            userMeal.setUser(user);
            userMeals.add((UserMeal) super.create(userMeal));
        });
        return userRecipeService.create(UserRecipe.newBuilder().withUserRecipe((UserRecipe) o).withShared(false)
                .withUserRecipeId(SpecialIdEnum.NOT_USED.getId()).withUserMeals(userMeals).build());
    }
}
