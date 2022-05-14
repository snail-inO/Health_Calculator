package project.ood.healthcalculator.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.ood.healthcalculator.entity.*;
import project.ood.healthcalculator.enums.*;
import project.ood.healthcalculator.service.CRUD.*;
import project.ood.healthcalculator.service.NutrientCalculationService;
import project.ood.healthcalculator.service.NutrientCalculationServiceImpl;
import project.ood.healthcalculator.utils.CustomException;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/nutrient_counter")
@Api(value = "Recipe nutrition calculator APIs")
public class NutrientCounterController {
    private final NutrientCounterServiceImpl nutrientCounterService;
    private final UserServiceImpl userService;
    private final UserRecipeServiceImpl userRecipeService;
    private final UserMealServiceImpl userMealService;
    private final UserFoodServiceImpl userFoodsService;

    public NutrientCounterController(NutrientCounterServiceImpl nutrientCounterService, UserServiceImpl userService,
                                     UserRecipeServiceImpl userRecipeService, UserMealServiceImpl userMealService,
                                     UserFoodServiceImpl userFoodsService) {
        this.nutrientCounterService = nutrientCounterService;
        this.userService = userService;
        this.userRecipeService = userRecipeService;
        this.userMealService = userMealService;
        this.userFoodsService = userFoodsService;
    }

    @PostMapping
    @ApiOperation(value = "Calculate and save user's recipe/meal/food's nutrition summary")
    public Iterable<NutrientCounter> calculateRecipeNutrient(@RequestParam int typeId, @RequestParam long id,
                                                             @ApiIgnore HttpSession session) {
        long uid = (long) session.getAttribute("userId");
        List<NutrientCounter> nutrientCounters;
        NutrientCalculationService nutrientCalculationService = new NutrientCalculationServiceImpl();

        if (typeId == EntityTypeEnum.USER.ordinal()) {
            User user = userService.retrieve(uid);
            nutrientCounters = user.getNutrientCounters();
            if (!nutrientCounters.isEmpty())
                return nutrientCounters;
            return nutrientCounterService.createAll(nutrientCalculationService.calculateNutrition(user).values());
        } else if (typeId == EntityTypeEnum.RECIPE.ordinal()) {
            UserRecipe userRecipe = userRecipeService.retrieve(id);
            if (userRecipe.getUser().getUid() != uid)
                throw new CustomException(RestEnum.UNAUTHORIZED);
            nutrientCounters = userRecipe.getNutrientCounters();
            if (!nutrientCounters.isEmpty())
                return nutrientCounters;
            return nutrientCounterService.createAll(nutrientCalculationService.calculateNutrition(userRecipe).values());
        } else if (typeId == EntityTypeEnum.MEAL.ordinal()) {
            UserMeal userMeal = userMealService.retrieve(id);
            if (userMeal.getUser().getUid() != uid)
                throw new CustomException(RestEnum.UNAUTHORIZED);
            nutrientCounters = userMeal.getNutrientCounters();
            if (!nutrientCounters.isEmpty())
                return nutrientCounters;
            return nutrientCounterService.createAll(nutrientCalculationService.calculateNutrition(userMeal).values());
        } else if (typeId == EntityTypeEnum.FOOD.ordinal()) {
            UserFoods userFoods = userFoodsService.retrieve(id);
            if (userFoods.getUser().getUid() != uid)
                throw new CustomException(RestEnum.UNAUTHORIZED);
            nutrientCounters = userFoods.getNutrientCounters();
            if (!nutrientCounters.isEmpty())
                return nutrientCounters;
            return nutrientCounterService.createAll(nutrientCalculationService.calculateNutrition(userFoods).values());
        }

        throw new CustomException(RestEnum.BAD_REQUEST);
    }

    @GetMapping
    @ApiOperation(value = "Get recommended nutrition intake of user's type")
    private Iterable<NutrientCounter> getRecommendedIntake(@ApiIgnore HttpSession session) {
        int userTypeId = userService.retrieve((long) session.getAttribute("userId")).getUserType();

        return nutrientCounterService.retrieveAll(UserRecipe.newBuilder().withUserRecipeId(userTypeId).build());
    }
}
