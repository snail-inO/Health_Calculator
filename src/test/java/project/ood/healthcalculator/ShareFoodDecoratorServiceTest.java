package project.ood.healthcalculator;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import project.ood.healthcalculator.dao.UserDAO;
import project.ood.healthcalculator.dao.UserFoodsDAO;
import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;
import project.ood.healthcalculator.enums.RestEnum;
import project.ood.healthcalculator.enums.SpecialIdEnum;
import project.ood.healthcalculator.service.CRUD.*;
import project.ood.healthcalculator.service.ShareFoodDecoratorServiceImpl;
import project.ood.healthcalculator.service.UserRepositoryDecoratorServiceImpl;
import project.ood.healthcalculator.utils.CustomException;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShareFoodDecoratorServiceTest {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserFoodServiceImpl userFoodService;
    @MockBean
    UserDAO userDAO;
    @MockBean
    UserFoodsDAO userFoodsDAO;

    @Test
    public void addFoodShare() {
        CRUDService<UserFoods> shareFoodService = new ShareFoodDecoratorServiceImpl(
                new UserRepositoryDecoratorServiceImpl<UserFoods>(userService, userFoodService));
        User user1 = User.newBuilder().withUid(SpecialIdEnum.NOT_USED.getId()).withUserName("t1")
                .withPassword("123").withUid(1).build();
        UserFoods userFoods = UserFoods.newBuilder().withUserFoodsId(1).withUser(user1).withValue(10)
                .withFood(Food.newBuilder().withFdcId(1).build()).build();

        Mockito.when(userDAO.findById(1L)).thenReturn(Optional.of(user1));
        Mockito.when(userDAO.save(Mockito.argThat(user -> user.getFoodCount() == 1)))
                .thenThrow(new CustomException(RestEnum.SUCCESS));
        Mockito.when(userFoodsDAO.save(Mockito.any(UserFoods.class))).thenReturn(userFoods);

        CustomException e = Assert.assertThrows(CustomException.class, () -> shareFoodService.create(userFoods));
        Assert.assertEquals(RestEnum.SUCCESS.getRestCode(), e.getMessage());
    }

}
