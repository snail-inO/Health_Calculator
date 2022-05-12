package project.ood.healthcalculator.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserMeal;
import project.ood.healthcalculator.entity.UserRecipe;
import project.ood.healthcalculator.enums.RestEnum;
import project.ood.healthcalculator.enums.SpecialIdEnum;
import project.ood.healthcalculator.service.*;
import project.ood.healthcalculator.service.CRUD.*;
import project.ood.healthcalculator.utils.CustomException;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user/recipe")
@Api(value = "user recipe repository CRUD APIs")
public class UserRecipeController {
    private final UserRecipeServiceImpl userRecipeServiceA;
    private final UserMealServiceImpl userMealService;
    private final CRUDService<UserRecipe> userRecipeService;
    private final CRUDService shareRecipeService;

    public UserRecipeController(UserRecipeServiceImpl userRecipeServiceA,
                                UserMealServiceImpl userMealService, UserServiceImpl userService,
                                UserFoodServiceImpl userFoodService) {
        this.userRecipeServiceA = userRecipeServiceA;
        this.userMealService = userMealService;
        this.userRecipeService = new UserRepositoryDecoratorServiceImpl<UserRecipe>(userService, userRecipeServiceA);
        CRUDService shareService = new UserRepositoryDecoratorServiceImpl(userService, userFoodService);
        shareService = new ShareFoodDecoratorServiceImpl(shareService);
        shareService = new ShareMealDecoratorServiceImpl(shareService,
                new UserRepositoryDecoratorServiceImpl<UserMeal>(userService, userMealService));
        this.shareRecipeService = new ShareRecipeDecoratorServiceImpl(shareService, userRecipeService);
    }

    @GetMapping
    @ApiOperation(value = "Retrieve user's recipes")
    public List<UserRecipe> showRecipe(@RequestParam(required = false, defaultValue = "0") boolean share,
                                       @ApiIgnore HttpSession session) {
        if (share)
            return userRecipeServiceA.retrieveAll(true);

        return userRecipeServiceA.retrieveAll(User.newBuilder().withSession(session).build());
    }

    @PostMapping
    @ApiOperation(value = "Create user's recipe with user meal id(s)")
    @ApiImplicitParam(name = "sequenceId", value = "sequence number of the recipe, natural sequence, maximum = 30")
    public UserRecipe createRecipe(@RequestParam long[] mealId, @RequestParam(required = false) String name,
                                   @RequestParam(required = false, defaultValue = "-1") int sequenceId,
                                   @ApiIgnore HttpSession session) {
        if (sequenceId > 30)
            sequenceId = -1;
        List<UserMeal> userMeals = new ArrayList<>();
        for (long id : mealId) {
            userMeals.add(userMealService.retrieve(id));
        }

        return userRecipeService.create(UserRecipe.newBuilder().withUserRecipeId(SpecialIdEnum.NOT_USED.getId())
                .withName(name).withSequenceId(sequenceId).withUserMeals(userMeals)
                .withUser(User.newBuilder().withSession(session).build()).build());
    }

    @DeleteMapping
    @ApiOperation(value = "Delete user's recipe(s) by user recipe id(s)")
    public UserRecipe deleteRecipe(@RequestParam long[] id) {
        for (long i : id) {
            userRecipeService.delete(i);
        }
        return null;
    }

    @PatchMapping
    @ApiOperation(value = "Update one user's recipe with recipe id")
    @ApiImplicitParam(name = "sequenceId", value = "sequence number of the recipe, natural sequence, maximum = 30")
    public UserRecipe updateRecipe(@RequestParam long id, @RequestParam(required = false) long[] mealId,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(required = false, defaultValue = "-1") int sequenceId,
                                   @RequestParam(required = false, defaultValue = "0") boolean share,
                                   @ApiIgnore HttpSession session) {
        if (sequenceId > 30)
            sequenceId = -1;
        List<UserMeal> userMeals = new ArrayList<>();
        for (long i : mealId) {
            userMeals.add(userMealService.retrieve(i));
        }
        UserRecipe userRecipe = UserRecipe.newBuilder().withUserRecipeId(id).withName(name).withSequenceId(sequenceId)
                .withShared(share).withUserMeals(userMeals).withUser(User.newBuilder().withSession(session).build()).build();

        return userRecipeService.update(userRecipe);
    }

    @PostMapping("/share")
    @ApiOperation(value = "Add shared recipe to user repository")
    public UserRecipe addSharedRecipe(@RequestParam long recipeId, @ApiIgnore HttpSession session) {
        UserRecipe userRecipe = userRecipeService.retrieve(recipeId);

        if (!userRecipe.isShared())
            throw new CustomException(RestEnum.BAD_REQUEST);
        userRecipe.setUser(User.newBuilder().withSession(session).build());

        return (UserRecipe) shareRecipeService.create(userRecipe);
    }
}
