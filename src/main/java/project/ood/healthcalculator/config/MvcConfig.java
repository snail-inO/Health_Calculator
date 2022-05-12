package project.ood.healthcalculator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import project.ood.healthcalculator.interceptor.LoginInterceptor;
import project.ood.healthcalculator.service.factory.UserCRUDCreatorServiceImpl;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    private final UserCRUDCreatorServiceImpl userService;

    public MvcConfig(UserCRUDCreatorServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(userService)).addPathPatterns("/user/**")
                .excludePathPatterns("/user/login");
    }
}
