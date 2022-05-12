package project.ood.healthcalculator.service.CRUD;

import org.springframework.stereotype.Service;
import project.ood.healthcalculator.dao.UserDAO;
import project.ood.healthcalculator.entity.User;

import javax.servlet.http.HttpSession;

public interface UserService {
    User retrieve(String name);
}
