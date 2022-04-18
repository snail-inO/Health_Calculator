package project.ood.healthcalculator.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.ood.healthcalculator.dao.UserDAO;
import project.ood.healthcalculator.entity.User;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.stream.Stream;

@RestController
public class UserController {
    @Autowired
    UserDAO userDAO;

    @PostMapping("/user/login")
    public User login(HttpServletRequest request) {
        User user = new User();
        user.setUserName("test");
        user.setPassword("123");
        try {
            userDAO.save(user);
        } catch (Exception e) {

        }

        HttpSession session = request.getSession();
        user = userDAO.findByUserName("test");
        session.setAttribute("userId", user.getUid());

        return user;
    }
}
