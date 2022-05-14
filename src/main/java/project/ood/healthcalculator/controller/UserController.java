package project.ood.healthcalculator.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.enums.RestEnum;
import project.ood.healthcalculator.service.CRUD.UserServiceImpl;
import project.ood.healthcalculator.utils.CustomException;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@Api(value = "User Operations APIs")
public class UserController {
    private final UserServiceImpl userService;


    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    @ApiOperation(value = "User login API", notes = "Now only support test user")
    public User login(@RequestParam String username, @RequestParam String password,
                      @ApiIgnore HttpServletRequest request) {
        User user = userService.retrieve(username);
        if (!user.getPassword().equals(password))
            throw new CustomException(RestEnum.BAD_REQUEST);
        HttpSession session = request.getSession();

        session.setAttribute("userId", user.getUid());

        return user;
    }

    @GetMapping("/logout")
    @ApiOperation(value = "logout current user")
    public Long logout(@ApiIgnore HttpSession session) {
        Long uid = (Long) session.getAttribute("userId");
        session.removeAttribute("userId");

        return uid;
    }

    @GetMapping
    @ApiOperation(value = "Retrieve user information with uid")
    public User retrieveUser(@RequestParam long uid) {
        return userService.retrieve(uid);
    }

    @PatchMapping
    @ApiOperation(value = "Update user information")
    public User updateUser(@RequestParam(required = false) String name,
                           @RequestParam(required = false) String passwd, @RequestParam(required = false) int type,
                           @RequestParam(required = false) int foodCount,@RequestParam(required = false) int age,
                           @ApiIgnore HttpSession session) {
        User user = userService.retrieve((Long) session.getAttribute("userId"));
        return userService.update(User.newBuilder().withUser(user).withUserName(name).withPassword(passwd)
                .withUserType(type).withFoodCounts(foodCount).withAge(age).build());
    }
}
