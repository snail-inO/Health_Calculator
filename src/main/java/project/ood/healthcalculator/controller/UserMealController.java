package project.ood.healthcalculator.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;
import project.ood.healthcalculator.entity.UserMeal;
import project.ood.healthcalculator.enums.RestEnum;
import project.ood.healthcalculator.enums.SpecialIdEnum;
import project.ood.healthcalculator.service.CRUD.UserFoodServiceImpl;
import project.ood.healthcalculator.service.CRUD.UserMealServiceImpl;
import project.ood.healthcalculator.service.ShareFoodDecoratorServiceImpl;
import project.ood.healthcalculator.service.ShareMealDecoratorServiceImpl;
import project.ood.healthcalculator.service.UserRepositoryDecoratorServiceImpl;
import project.ood.healthcalculator.service.CRUD.UserServiceImpl;
import project.ood.healthcalculator.service.CRUD.CRUDService;
import project.ood.healthcalculator.utils.CustomException;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user/meal")
@Api(value = "User meal repository CRUD APIs")
public class UserMealController {
    private final UserMealServiceImpl userMealServiceA;
    private final UserFoodServiceImpl userFoodService;
    private final CRUDService<UserMeal> userMealService;
    private final CRUDService shareMealService;

    public UserMealController(UserMealServiceImpl userMealServiceA,
                              UserFoodServiceImpl userFoodService, UserServiceImpl userService) {
        this.userMealServiceA = userMealServiceA;
        this.userFoodService = userFoodService;
        this.userMealService = new UserRepositoryDecoratorServiceImpl<UserMeal>(userService, userMealServiceA);
        CRUDService shareService = new UserRepositoryDecoratorServiceImpl(userService, userFoodService);
        shareService = new ShareFoodDecoratorServiceImpl(shareService);
        this.shareMealService = new ShareMealDecoratorServiceImpl(shareService, userMealService);
    }

    @PostMapping
    @ApiOperation(value = "Create Meal with user food id")
    public UserMeal createMeal(@RequestParam long[] foodId, @RequestParam(required = false) String name,
                               @RequestParam(required = false) String description,
                               @RequestParam(required = false, defaultValue = "-1") int type,
                               @RequestParam(required = false) String tag, @ApiIgnore HttpSession session) {
        List<UserFoods> userFoods = new ArrayList<>();
        for (long id : foodId) {
            userFoods.add(userFoodService.retrieve(id));
        }
        return userMealService.create(UserMeal.newBuilder().withUserMealId(SpecialIdEnum.NOT_USED.getId()).withName(name)
                .withDescription(description)
                .withTag(tag).withType(type).withUserFoods(userFoods)
                .withUser(User.newBuilder().withSession(session).build()).build());
    }

    @GetMapping
    @ApiOperation(value = "Retrieve user's meals")
    public List<UserMeal> showMeal(@RequestParam(required = false, defaultValue = "0") boolean share,
                                   @ApiIgnore HttpSession session) {
        if (share)
            return userMealServiceA.retrieveAll(true);

        return userMealServiceA.retrieveAll(User.newBuilder().withSession(session).build());
    }

    @DeleteMapping
    @ApiOperation(value = "Delete user's meal by meal id")
    public UserMeal deleteMeal(@RequestParam long id) {
        userMealService.delete(id);
        return null;
    }

    @PatchMapping
    @ApiOperation(value = "Update user's meal data")
    public UserMeal updateMeal(@RequestParam long mealId, @RequestParam(required = false) long[] foodId,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String description,
                               @RequestParam(required = false, defaultValue = "0") int type,
                               @RequestParam(required = false) String tag,
                               @RequestParam(required = false, defaultValue = "0") boolean share,
                               @ApiIgnore HttpSession session) {
        List<UserFoods> userFoods = new ArrayList<>();
        for (long id : foodId) {
            userFoods.add(userFoodService.retrieve(id));
        }
        UserMeal userMeal = UserMeal.newBuilder().withUserMealId(mealId).withName(name).withDescription(description)
                .withTag(tag).withType(type).withUserFoods(userFoods).withShared(share)
                .withUser(User.newBuilder().withSession(session).build()).withShared(share).build();

        return userMealService.update(userMeal);
    }

    @PostMapping("/share")
    @ApiOperation(value = "Add shared meal to user repository")
    public UserMeal addSharedMeal(@RequestParam long mealId, @ApiIgnore HttpSession session) {
        UserMeal meal = userMealService.retrieve(mealId);

        if (!meal.isShared())
            throw new CustomException(RestEnum.BAD_REQUEST);
        meal.setUser(User.newBuilder().withSession(session).build());

        return (UserMeal) shareMealService.create(meal);
    }
}
