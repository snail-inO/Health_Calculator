package project.ood.healthcalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.ood.healthcalculator.entity.*;
import project.ood.healthcalculator.enums.SpecialIdEnum;

import project.ood.healthcalculator.service.CRUD.*;
import project.ood.healthcalculator.service.ShareFoodDecoratorServiceImpl;
import project.ood.healthcalculator.service.ShareMealDecoratorServiceImpl;
import project.ood.healthcalculator.service.ShareRecipeDecoratorServiceImpl;
import project.ood.healthcalculator.service.UserRepositoryDecoratorServiceImpl;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ShareRecipeDecoratorServiceTest {
    private final CRUDService shareRecipeService;
    private final UserServiceImpl userService;

    private final UserRecipe userRecipe;

    @Autowired
    public ShareRecipeDecoratorServiceTest(UserRecipeServiceImpl userRecipeService,
                                           UserMealServiceImpl userMealService, UserServiceImpl userService,
                                           UserFoodServiceImpl userFoodService) {
        this.userService = userService;
        CRUDService shareService = new UserRepositoryDecoratorServiceImpl(userService, userFoodService);
        shareService = new ShareFoodDecoratorServiceImpl(shareService);
        shareService = new ShareMealDecoratorServiceImpl(shareService,
                new UserRepositoryDecoratorServiceImpl<UserMeal>(userService, userMealService));
        this.shareRecipeService = new ShareRecipeDecoratorServiceImpl(shareService,
                new UserRepositoryDecoratorServiceImpl<UserRecipe>(userService, userRecipeService));

        User user = User.newBuilder().withUid(2).build();
        UserFoods userFood = UserFoods.newBuilder().withFood(Food.newBuilder().withFdcId(454004).build())
                .withUserFoodsId(SpecialIdEnum.NOT_USED.getId()).withUser(user).withValue(100).build();

        long userFoodId = userFoodService.create(userFood).getUserFoodsId();
        userFood.setUserFoodsId(userFoodId);
        List<UserFoods> userFoods = new ArrayList<>();
        userFoods.add(userFood);


        UserMeal userMeal = UserMeal.newBuilder().withUser(user)
                .withUserMealId(SpecialIdEnum.NOT_USED.getId()).withUserFoods(userFoods).build();

        long userMealId = userMealService.create(userMeal).getUserMealId();
        userMeal.setUserMealId(userMealId);
        List<UserMeal> userMeals = new ArrayList<>();
        userMeals.add(userMeal);

        this.userRecipe = UserRecipe.newBuilder().withUser(user).withUserMeals(userMeals)
                .withShared(true).withUserRecipeId(SpecialIdEnum.NOT_USED.getId()).build();
        long userRecipeId = userRecipeService.create(this.userRecipe).getUserRecipeId();
        user.setUid(3);
        this.userRecipe.setUser(user);
        this.userRecipe.setUserRecipeId(userRecipeId);
    }

    @Test
    public void addRecipeFromShare() {
        int foodCountBefore = userService.retrieve(3).getFoodCount();
        int mealCountBefore = userService.retrieve(3).getMealCount();
        int recipeCountBefore = userService.retrieve(3).getRecipeCount();

        Assertions.assertDoesNotThrow(() -> shareRecipeService.create(userRecipe));
        int foodCountAfter = userService.retrieve(3).getFoodCount();
        int mealCountAfter = userService.retrieve(3).getMealCount();
        int recipeCountAfter = userService.retrieve(3).getRecipeCount();

        Assertions.assertEquals(foodCountBefore + 1, foodCountAfter);
        Assertions.assertEquals(mealCountBefore + 1, mealCountAfter);
        Assertions.assertEquals(recipeCountBefore + 1, recipeCountAfter);
    }

}
