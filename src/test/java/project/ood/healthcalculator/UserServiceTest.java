package project.ood.healthcalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.enums.SpecialIdEnum;
import project.ood.healthcalculator.enums.UserTypeEnum;
import project.ood.healthcalculator.service.CRUD.UserServiceImpl;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserServiceImpl userService;

    private final String username = "test1";
    private final String password = "123";

    @Test
    public void createUser() {
        String username = "test_user";
        String password = "1234567";
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> userService.create(User.newBuilder().build()));
        User user = userService.create(User.newBuilder().withUid(SpecialIdEnum.NOT_USED.getId()).withUserName(username)
                .withPassword(password).withUserType(UserTypeEnum.PREGNANCY.ordinal()).build());
        Assertions.assertEquals(username, user.getUserName());
    }

    @Test
    public void retrieveUser() {
        User user = userService.retrieve(username);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(username, user.getUserName());
        Assertions.assertEquals(password, user.getPassword());
    }

    @Test
    public void updateUser() {
        User user = userService.retrieve(username);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(UserTypeEnum.AVERAGE.ordinal(), user.getUserType());
        user.setUserType(UserTypeEnum.PREGNANCY.ordinal());
        user.setFoodCount(1);
        user = userService.update(user);
        Assertions.assertEquals(UserTypeEnum.PREGNANCY.ordinal(), user.getUserType());
        Assertions.assertEquals(1, user.getFoodCount());
    }
}
