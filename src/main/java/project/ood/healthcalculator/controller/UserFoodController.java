package project.ood.healthcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.ood.healthcalculator.dao.FoodDAO;
import project.ood.healthcalculator.dao.UserDAO;
import project.ood.healthcalculator.dao.UserFoodsDAO;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;
import project.ood.healthcalculator.enums.RestEnum;
import project.ood.healthcalculator.service.UserFoodServiceImpl;
import project.ood.healthcalculator.utils.CustomException;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserFoodController {
    @Autowired
    private UserFoodsDAO userFoodsDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private FoodDAO foodDAO;
    private User user;
    private UserFoodServiceImpl userFoodService;

    @PostMapping("/user_food")
    public UserFoods addFood(@RequestParam long fdcId, @RequestParam(required = false, defaultValue = "0") float value, HttpSession session) {
        initialize(session);
        return userFoodService.addFood(fdcId, value);
    }

    @DeleteMapping("/user_food")
    public UserFoods removeFood(@RequestParam long id, HttpSession session) {
        initialize(session);
        return userFoodService.removeFood(id);
    }

    @GetMapping("/user_food")
    public List<UserFoods> showFood(HttpSession session) {
        initialize(session);

        return userFoodService.showFoods();
    }
    @PatchMapping("/user_food")
    public UserFoods updateFood(@RequestParam long id, @RequestParam float value, HttpSession session) {
        initialize(session);
        return userFoodService.updateFood(id, value);
    }

    private void authenticate(HttpSession session) {
        long uid = (long) session.getAttribute("userId");
        user = userDAO.findByUid(uid);
        if (user == null)
            throw new CustomException(RestEnum.UNAUTHORIZED);
    }

    private void initialize(HttpSession session) {
        authenticate(session);
        if (userFoodService == null)
            userFoodService = new UserFoodServiceImpl(user, foodDAO, userFoodsDAO, userDAO);
    }
}
