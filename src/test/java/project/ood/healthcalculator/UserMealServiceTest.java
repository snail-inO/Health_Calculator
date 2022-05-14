package project.ood.healthcalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;
import project.ood.healthcalculator.entity.UserMeal;
import project.ood.healthcalculator.enums.SpecialIdEnum;
import project.ood.healthcalculator.service.CRUD.UserFoodServiceImpl;
import project.ood.healthcalculator.service.CRUD.UserMealServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
public class UserMealServiceTest {
    @Autowired
    private UserMealServiceImpl userMealService;
    private final List<UserFoods> userFoods;

    @Autowired
    public UserMealServiceTest(UserFoodServiceImpl userFoodService) {
        userFoods = new ArrayList<>();
        userFoods.add(userFoodService.create(UserFoods.newBuilder().withFood(Food.newBuilder().withFdcId(454004).build())
                .withUserFoodsId(SpecialIdEnum.NOT_USED.getId()).withUser(User.newBuilder().withUid(2).build())
                .withValue(100).build()));
    }

    @Test
    public void createMeal() {
        UserMeal userMeal = userMealService.create(UserMeal.newBuilder().withUser(User.newBuilder().withUid(2).build())
                .withUserMealId(SpecialIdEnum.NOT_USED.getId()).withUserFoods(userFoods).build());

        Assertions.assertNotNull(userMeal);
        Assertions.assertEquals(userFoods.size(), userMeal.getUserFoods().size());
        Assertions.assertEquals(userFoods.get(0).getUserFoodsId(), userMeal.getUserFoods().get(0).getUserFoodsId());
    }

    @Test
    public void retrieveMeal() {
        long id = userMealService.create(UserMeal.newBuilder().withUser(User.newBuilder().withUid(2).build())
                .withUserMealId(SpecialIdEnum.NOT_USED.getId()).withUserFoods(userFoods).build()).getUserMealId();
        UserMeal userMeal = userMealService.retrieve(id);

        Assertions.assertNotNull(userMeal);
        Assertions.assertEquals(id, userMeal.getUserMealId());
    }

    @Test
    public void updateMeal() {
        UserMeal userMeal = userMealService.create(UserMeal.newBuilder().withUser(User.newBuilder().withUid(2).build())
                .withUserMealId(SpecialIdEnum.NOT_USED.getId()).withUserFoods(userFoods).build());

        Assertions.assertNotNull(userMeal);
        Assertions.assertNull(userMeal.getName());

        userMeal.setName("test meal");
        userMeal = userMealService.update(userMeal);

        Assertions.assertNotNull(userMeal);
        Assertions.assertEquals("test meal", userMeal.getName());
    }

    @Test
    public void deleteMeal() {
        long id = userMealService.create(UserMeal.newBuilder().withUser(User.newBuilder().withUid(2).build())
                .withUserMealId(SpecialIdEnum.NOT_USED.getId()).withUserFoods(userFoods).build()).getUserMealId();
        Assertions.assertDoesNotThrow(() -> userMealService.retrieve(id));

        userMealService.delete(id);
        Assertions.assertThrows(NoSuchElementException.class, () -> userMealService.retrieve(id));
    }
}
