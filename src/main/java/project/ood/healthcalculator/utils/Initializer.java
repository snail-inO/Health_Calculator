package project.ood.healthcalculator.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import project.ood.healthcalculator.entity.*;
import project.ood.healthcalculator.enums.AverageTypeNutritionEnum;
import project.ood.healthcalculator.enums.NutrientInitEnum;
import project.ood.healthcalculator.enums.SpecialIdEnum;
import project.ood.healthcalculator.enums.UserTypeEnum;
import project.ood.healthcalculator.service.CRUD.CRUDService;
import project.ood.healthcalculator.service.CRUD.NutrientCounterServiceImpl;
import project.ood.healthcalculator.service.CRUD.UserRecipeServiceImpl;
import project.ood.healthcalculator.service.CRUD.UserServiceImpl;
import project.ood.healthcalculator.service.FoodSearchServiceImpl;
import project.ood.healthcalculator.service.SaveDataServiceImpl;
import project.ood.healthcalculator.service.UserRepositoryDecoratorServiceImpl;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class Initializer implements InitializingBean {
    private final UserServiceImpl userService;
    private final CRUDService userRecipeService;
    private final NutrientCounterServiceImpl nutrientCounterService;
    private final FoodSearchServiceImpl foodSearchService;
    private final SaveDataServiceImpl saveDataService;

    public Initializer(UserServiceImpl userService, UserRecipeServiceImpl userRecipeService,
                                       NutrientCounterServiceImpl nutrientCounterService,
                                       FoodSearchServiceImpl foodSearchService, SaveDataServiceImpl saveDataService) {
        this.userService = userService;
        this.nutrientCounterService = nutrientCounterService;
        this.foodSearchService = foodSearchService;
        this.saveDataService = saveDataService;
        this.userRecipeService = new UserRepositoryDecoratorServiceImpl(userService, userRecipeService);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Initializing");
        init();
        log.info("Initialized");
    }

    private void init() throws InterruptedException {
        initUser();
        initNutrient();
        TimeUnit.SECONDS.sleep(5);
        initUserTypeNutrient();
    }

    private void initUser() {
        userService.create(User.newBuilder().withUid(SpecialIdEnum.NOT_USED.getId()).withUserName("share")
                .withPassword("123").build());
        userService.create(User.newBuilder().withUid(SpecialIdEnum.NOT_USED.getId()).withUserName("test1")
                .withPassword("123").withUserType(UserTypeEnum.AVERAGE.ordinal()).build());
        userService.create(User.newBuilder().withUid(SpecialIdEnum.NOT_USED.getId()).withUserName("test2")
                .withPassword("123").withUserType(UserTypeEnum.AVERAGE.ordinal()).build());
    }

    private void initNutrient() {
        for (NutrientInitEnum it : NutrientInitEnum.values()) {
                List<Food> foods = foodSearchService.byName(it.getName(), it.getPageNum());
                foods.forEach(saveDataService::saveFood);
        }
    }

    private void initUserTypeNutrient() {
        UserRecipe userRecipe = (UserRecipe) userRecipeService.create(UserRecipe.newBuilder().withUser(User.newBuilder()
                .withUid(SpecialIdEnum.SHARE.getId()).build()).build());
        for (AverageTypeNutritionEnum it : AverageTypeNutritionEnum.values()) {
            if (it.getNutrientId() == 0)
                continue;
            nutrientCounterService.create(NutrientCounter.newBuilder().withValue(it.getValue())
                    .withNutrient(Nutrient.newBuilder().withNutrientId(it.getNutrientId()).build())
                    .withUser(User.newBuilder().withUid(SpecialIdEnum.SHARE.getId()).build())
                    .withUserRecipe(userRecipe)
                    .withNutrientCounterId(SpecialIdEnum.NOT_USED.getId()).build());
        }
    }
}
