package project.ood.healthcalculator.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.service.factory.UserCRUDCreatorServiceImpl;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@Api(value = "User Operations APIs")
public class UserController {
    private final UserCRUDCreatorServiceImpl userService;

    public UserController(UserCRUDCreatorServiceImpl userService) {
        this.userService = userService;
        userService.createCRUDService();
    }

    @PostMapping("/login")
    @ApiOperation(value = "User login API", notes = "Now only support test user")
    public User login(@ApiIgnore HttpServletRequest request) {
        User user = new User();
        user.setUserName("test");
        user.setPassword("123");
        try {
            userService.create(user);
        } catch (Exception e) {

        }

        HttpSession session = request.getSession();
        user = userService.retrieve(user.getUserName());
        session.setAttribute("userId", user.getUid());

        return user;
    }
}
