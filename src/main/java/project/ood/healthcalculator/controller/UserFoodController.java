package project.ood.healthcalculator.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;
import project.ood.healthcalculator.enums.SpecialIdEnum;
import project.ood.healthcalculator.service.CRUD.CRUDService;
import project.ood.healthcalculator.service.CRUD.UserFoodServiceImpl;
import project.ood.healthcalculator.service.CRUD.UserServiceImpl;
import project.ood.healthcalculator.service.UserRepositoryDecoratorServiceImpl;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user/food")
@Api(value = "User food repository CRUD APIs")
public class UserFoodController {
    private final UserFoodServiceImpl userFoodServiceA;
    private final CRUDService<UserFoods> userFoodService;

    public UserFoodController(UserFoodServiceImpl userFoodServiceA, UserServiceImpl userService) {
        this.userFoodServiceA = userFoodServiceA;
        this.userFoodService = new UserRepositoryDecoratorServiceImpl<UserFoods>(userService, userFoodServiceA);
    }

    @PostMapping
    @ApiOperation(value = "Add food into user repository by food fdcId")
    @ApiImplicitParam(name = "value", value = "Quantity of the food, default value is the food package weight in database")
    public UserFoods addFood(@RequestParam long fdcId, @RequestParam float value, @ApiIgnore HttpSession session) {

        return userFoodService.create(UserFoods.newBuilder().withUserFoodsId(SpecialIdEnum.NOT_USED.getId())
                .withUser(User.newBuilder().withSession(session).build()).withValue(value)
                .withFood(Food.newBuilder().withFdcId(fdcId).build()).build());
    }

    @DeleteMapping
    @ApiOperation(value = "Delete food from user repository by fdcId")
    public UserFoods removeFood(@RequestParam long id) {
        userFoodService.delete(id);
        return null;
    }

    @GetMapping
    @ApiOperation(value = "List all the foods in user repository")
    public List<UserFoods> showFood(@ApiIgnore HttpSession session) {
        return userFoodServiceA.retrieveAll(User.newBuilder().withSession(session).build());
    }
    @PatchMapping
    @ApiOperation(value = "Update specific food's quantity in user repository")
    public UserFoods updateFood(@RequestParam long id, @RequestParam float value) {

        return userFoodService.update(UserFoods.newBuilder().withUserFoods(userFoodService.retrieve(id)).withValue(value)
                .build());
    }
}
