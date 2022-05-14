package project.ood.healthcalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;
import project.ood.healthcalculator.enums.SpecialIdEnum;
import project.ood.healthcalculator.service.CRUD.CRUDService;
import project.ood.healthcalculator.service.CRUD.UserFoodServiceImpl;
import project.ood.healthcalculator.service.CRUD.UserServiceImpl;
import project.ood.healthcalculator.service.UserRepositoryDecoratorServiceImpl;

@SpringBootTest
public class UserRepositoryDecoratorServiceTest {
    private final CRUDService<UserFoods> userFoodsService;
    private final UserServiceImpl userService;
    private final UserFoods userFoods;

    @Autowired
    public UserRepositoryDecoratorServiceTest(UserFoodServiceImpl userFoodService, UserServiceImpl userService) {
        this.userService = userService;
        this.userFoodsService = new UserRepositoryDecoratorServiceImpl(userService, userFoodService);
        userFoods = UserFoods.newBuilder().withFood(Food.newBuilder().withFdcId(454004).build())
                .withUserFoodsId(SpecialIdEnum.NOT_USED.getId()).withUser(User.newBuilder().withUid(2).build())
                .withValue(100).build();
    }

    @Test
    public void createFood() {
        int countBefore = userService.retrieve(2).getFoodCount();
        Assertions.assertDoesNotThrow(() -> userFoodsService.create(userFoods));
        int countAfter = userService.retrieve(2).getFoodCount();

        Assertions.assertEquals(countBefore + 1, countAfter);
    }
}
