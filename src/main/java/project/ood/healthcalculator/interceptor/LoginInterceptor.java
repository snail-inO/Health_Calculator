package project.ood.healthcalculator.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import project.ood.healthcalculator.service.CRUD.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    private final UserServiceImpl userService;

    public LoginInterceptor(UserServiceImpl userService) {
        this.userService = userService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();

        try {
            userService.retrieve((long) httpSession.getAttribute("userId"));
        } catch (Exception e) {
            request.getRequestDispatcher("/user/login").forward(request, response);
            return false;
        }
        return true;
    }
}
