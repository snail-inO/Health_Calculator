package project.ood.healthcalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;
import project.ood.healthcalculator.enums.SpecialIdEnum;
import project.ood.healthcalculator.service.CRUD.UserFoodServiceImpl;

import java.util.NoSuchElementException;

@SpringBootTest
public class UserFoodServiceTest {
    @Autowired
    private UserFoodServiceImpl userFoodService;

    private final long fdcId = 454004;

    @Test
    public void createUserFood() {
        UserFoods userFoods = userFoodService.create(UserFoods.newBuilder().withFood(Food.newBuilder().withFdcId(fdcId).build())
                .withUserFoodsId(SpecialIdEnum.NOT_USED.getId()).withUser(User.newBuilder().withUid(2).build())
                .withValue(100).build());

        Assertions.assertNotNull(userFoods);
        Assertions.assertEquals(fdcId, userFoods.getFood().getFdcId());
    }

    @Test void retrieveUserFood() {
        long id = userFoodService.create(UserFoods.newBuilder().withFood(Food.newBuilder().withFdcId(fdcId).build())
                .withUserFoodsId(SpecialIdEnum.NOT_USED.getId()).withUser(User.newBuilder().withUid(2).build())
                .withValue(100).build()).getUserFoodsId();

        UserFoods userFoods = userFoodService.retrieve(id);
        Assertions.assertNotNull(userFoods);
        Assertions.assertEquals(fdcId, userFoods.getFood().getFdcId());
        Assertions.assertEquals(2, userFoods.getUser().getUid());
        Assertions.assertEquals(100, userFoods.getValue());
    }

    @Test void updateUserFood() {
        UserFoods userFoods = userFoodService.create(UserFoods.newBuilder().withFood(Food.newBuilder().withFdcId(fdcId).build())
                .withUserFoodsId(SpecialIdEnum.NOT_USED.getId()).withUser(User.newBuilder().withUid(2).build())
                .withValue(100).build());

        userFoods.setValue(88);
        userFoods = userFoodService.update(userFoods);
        Assertions.assertEquals(88, userFoods.getValue());
    }
    @Test
    public void deleteUserFood() {
        UserFoods userFoods = userFoodService.create(UserFoods.newBuilder().withFood(Food.newBuilder().withFdcId(fdcId).build())
                .withUserFoodsId(SpecialIdEnum.NOT_USED.getId()).withUser(User.newBuilder().withUid(2).build())
                .withValue(100).build());

        Assertions.assertNotNull(userFoods);
        Assertions.assertEquals(fdcId, userFoods.getFood().getFdcId());
        long id = userFoods.getUserFoodsId();
        userFoodService.delete(id);
        Assertions.assertThrows(NoSuchElementException.class, () -> userFoodService.retrieve(id));
    }
}
