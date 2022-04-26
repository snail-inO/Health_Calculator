package project.ood.healthcalculator.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;
import project.ood.healthcalculator.enums.RestEnum;
import project.ood.healthcalculator.service.builder.*;
import project.ood.healthcalculator.service.factory.UserCRUDCreatorServiceImpl;
import project.ood.healthcalculator.service.factory.UserFoodsCRUDCreatorServiceImpl;
import project.ood.healthcalculator.utils.CustomException;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user_food")
@Api(value = "User food repository CRUD APIs")
public class UserFoodController {
    private User user;
    private final UserFoodsCRUDCreatorServiceImpl userFoodService;
    private final UserCRUDCreatorServiceImpl userService;

    public UserFoodController(UserFoodsCRUDCreatorServiceImpl userFoodService, UserCRUDCreatorServiceImpl userService) {
        this.userFoodService = userFoodService;
        this.userService = userService;
        userService.createCRUDService();
    }

    @PostMapping
    @ApiOperation(value = "Add food into user repository by food fdcId")
    @ApiImplicitParam(name = "value", value = "Quantity of the food, default value is the food package weight in database")
    public UserFoods addFood(@RequestParam long fdcId, @RequestParam(required = false, defaultValue = "0") float value,
                             @ApiIgnore HttpSession session) throws InstantiationException, IllegalAccessException {
        authenticate(session);
        UserFoodsBuilderService userFoodsBuilderService = EntityDirectorService.createBuilder(UserFoodsBuilderServiceImpl.class);
        FoodBuilderService foodBuilderService = EntityDirectorService.createBuilder(FoodBuilderServiceImpl.class);
        return userFoodService.create(userFoodsBuilderService.setId(-1)
                .setFoodUser(foodBuilderService.setId(fdcId).build(), user)
                .setValue(value).build());
    }

    @DeleteMapping
    @ApiOperation(value = "Delete food from user repository by fdcId")
    public UserFoods removeFood(@RequestParam long id, @ApiIgnore HttpSession session) {
        authenticate(session);
        userFoodService.delete(id);
        return null;
    }

    @GetMapping
    @ApiOperation(value = "List all the foods in user repository")
    public List<UserFoods> showFood(@ApiIgnore HttpSession session) {
        authenticate(session);
        return userFoodService.retrieveAll();
    }
    @PatchMapping
    @ApiOperation(value = "Update specific food's quantity in user repository")
    public UserFoods updateFood(@RequestParam long id, @RequestParam float value, @ApiIgnore HttpSession session) throws
            InstantiationException, IllegalAccessException {
        authenticate(session);
        UserFoodsBuilderService builderService = EntityDirectorService.createBuilder(UserFoodsBuilderServiceImpl.class);
        return userFoodService.update(builderService.setUserFoods(userFoodService.retrieve(id)).setValue(value).build());
    }

    private void authenticate(HttpSession session) {
        if (user != null)
            return;
        long uid = (long) session.getAttribute("userId");
        user = userService.retrieve(uid);
        if (user == null)
            throw new CustomException(RestEnum.UNAUTHORIZED);
        userFoodService.setUser(user);
        userFoodService.createCRUDService();
    }
}
