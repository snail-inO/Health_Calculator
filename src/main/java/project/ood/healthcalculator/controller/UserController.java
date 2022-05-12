package project.ood.healthcalculator.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import project.ood.healthcalculator.entity.Nutrient;
import project.ood.healthcalculator.entity.NutrientCounter;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserRecipe;
import project.ood.healthcalculator.enums.AverageTypeNutritionEnum;
import project.ood.healthcalculator.enums.SpecialIdEnum;
import project.ood.healthcalculator.enums.UserTypeEnum;
import project.ood.healthcalculator.service.CRUD.NutrientCounterServiceImpl;
import project.ood.healthcalculator.service.CRUD.UserRecipeServiceImpl;
import project.ood.healthcalculator.service.CRUD.UserServiceImpl;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@Api(value = "User Operations APIs")
public class UserController {
    private final UserServiceImpl userService;
    private final NutrientCounterServiceImpl nutrientCounterService;
    private final UserRecipeServiceImpl userRecipeService;

    public UserController(UserServiceImpl userService, NutrientCounterServiceImpl nutrientCounterService,
                          UserRecipeServiceImpl userRecipeService) {
        this.userService = userService;
        this.nutrientCounterService = nutrientCounterService;
        this.userRecipeService = userRecipeService;
    }

    @PostMapping("/login")
    @ApiOperation(value = "User login API", notes = "Now only support test user")
    public User login(@ApiIgnore HttpServletRequest request) {
        try {
            userService.create(User.newBuilder().withUid(SpecialIdEnum.NOT_USED.getId()).withUserName("share")
                    .withPassword("123").build());
            userService.create(User.newBuilder().withUid(SpecialIdEnum.NOT_USED.getId()).withUserName("test")
                    .withPassword("123").withUserType(UserTypeEnum.AVERAGE.ordinal()).build());
            for (AverageTypeNutritionEnum it : AverageTypeNutritionEnum.values()) {
                if (it.getNutrientId() == 0)
                    continue;
                userRecipeService.create(UserRecipe.newBuilder().withUser(User.newBuilder()
                        .withUid(SpecialIdEnum.SHARE.getId()).build()).build());
                nutrientCounterService.create(NutrientCounter.newBuilder().withValue(it.getValue())
                        .withNutrient(Nutrient.newBuilder().withNutrientId(it.getNutrientId()).build())
                        .withUser(User.newBuilder().withUid(SpecialIdEnum.SHARE.getId()).build())
                        .withUserRecipe(UserRecipe.newBuilder().withUserRecipeId(UserTypeEnum.AVERAGE.ordinal()).build())
                        .withNutrientCounterId(SpecialIdEnum.NOT_USED.getId()).build());
            }
        } catch (Exception e) {

        }

        HttpSession session = request.getSession();
        User user = userService.retrieve("test");
        session.setAttribute("userId", user.getUid());

        return user;
    }

    @GetMapping
    @ApiOperation(value = "Retrieve user information with uid")
    public User retrieveUser(@RequestParam long uid) {
        return userService.retrieve(uid);
    }

    @PatchMapping
    @ApiOperation(value = "Update user information")
    public User updateUser(@RequestParam long uid, @RequestParam(required = false) String name,
                           @RequestParam(required = false) String passwd, @RequestParam(required = false) int type,
                           @RequestParam(required = false) int foodCount,@RequestParam(required = false) int age) {

        return userService.update(User.newBuilder().withUid(uid).withUserName(name).withPassword(passwd).withUserType(type)
                .withFoodCounts(foodCount).withAge(age).build());
    }
}
