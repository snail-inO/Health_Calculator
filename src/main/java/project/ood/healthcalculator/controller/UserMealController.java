package project.ood.healthcalculator.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;
import project.ood.healthcalculator.entity.UserMeal;
import project.ood.healthcalculator.enums.RestEnum;
import project.ood.healthcalculator.service.builder.EntityDirectorService;
import project.ood.healthcalculator.service.builder.UserMealBuilderService;
import project.ood.healthcalculator.service.builder.UserMealBuilderServiceImpl;
import project.ood.healthcalculator.service.factory.UserCRUDCreatorServiceImpl;
import project.ood.healthcalculator.service.factory.UserFoodsCRUDCreatorServiceImpl;
import project.ood.healthcalculator.service.factory.UserMealCRUDCreatorServiceImpl;
import project.ood.healthcalculator.utils.CustomException;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user_meal")
@Api(value = "User meal repository CRUD APIs")
public class UserMealController {
    private User user;
    private UserMealCRUDCreatorServiceImpl userMealService;
    private final UserFoodsCRUDCreatorServiceImpl userFoodService;
    private final UserCRUDCreatorServiceImpl userService;

    public UserMealController(UserMealCRUDCreatorServiceImpl userMealService, UserFoodsCRUDCreatorServiceImpl userFoodService,
                              UserCRUDCreatorServiceImpl userService) {
        this.userService = userService;
        this.userMealService = userMealService;
        this.userFoodService = userFoodService;
    }

    @PostMapping
    @ApiOperation(value = "Create Meal with user food id")
    public UserMeal createMeal(@RequestParam long[] foodId, @RequestParam(required = false) String name,
                               @RequestParam(required = false) String description,
                               @RequestParam(required = false, defaultValue = "-1") int type,
                               @RequestParam(required = false) String tag, @ApiIgnore HttpSession session) throws
            InstantiationException, IllegalAccessException {
        authenticate(session);
        List<UserFoods> userFoods = new ArrayList<>();
        for (long id : foodId) {
            userFoods.add(userFoodService.retrieve(id));
        }
        UserMealBuilderService userMealBuilderService = EntityDirectorService.createBuilder(UserMealBuilderServiceImpl.class);
        return userMealService.create(userMealBuilderService.setId(-1).setName(name).setDescription(description).setTag(tag)
                .setType(type).setUserFoods(userFoods).setUser(user).create());
    }

    @GetMapping
    @ApiOperation(value = "Retrieve user's meals")
    public List<UserMeal> showMeal(@ApiIgnore HttpSession session) {
        authenticate(session);
        return userMealService.retrieveAll();
    }

    @DeleteMapping
    @ApiOperation(value = "Delete user's meal by meal id")
    public UserMeal deleteMeal(@RequestParam long id, @ApiIgnore HttpSession session) {
        authenticate(session);
        userMealService.delete(id);
        return null;
    }

    @PatchMapping
    @ApiOperation(value = "Update user's meal data")
    public UserMeal updateMeal(@RequestParam long mealId, @RequestParam long[] foodId,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String description,
                               @RequestParam(required = false, defaultValue = "-1") int type,
                               @RequestParam(required = false) String tag, @ApiIgnore HttpSession session) throws
            InstantiationException, IllegalAccessException {
        authenticate(session);
        List<UserFoods> userFoods = new ArrayList<>();
        for (long id : foodId) {
            userFoods.add(userFoodService.retrieve(id));
        }

        UserMealBuilderService userMealBuilderService = EntityDirectorService.createBuilder(UserMealBuilderServiceImpl.class);
        return userMealService.update(userMealBuilderService.setId(mealId).setName(name).setDescription(description).setTag(tag)
                .setType(type).setUserFoods(userFoods).setUser(user).create());
    }

    private void authenticate(HttpSession session) {
        if (user != null)
            return;
        long uid = (long) session.getAttribute("userId");
        user = userService.retrieve(uid);
        if (user == null)
            throw new CustomException(RestEnum.UNAUTHORIZED);
        userMealService.setUser(user);
        userFoodService.setUser(user);
        userFoodService.createCRUDService();
        userMealService.createCRUDService();
    }
}
