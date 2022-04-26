package project.ood.healthcalculator.service;

import org.springframework.stereotype.Service;
import project.ood.healthcalculator.dao.UserDAO;
import project.ood.healthcalculator.entity.User;

public interface UserService {
    User retrieve(String name);
}
