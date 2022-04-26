package project.ood.healthcalculator.service;

import org.springframework.stereotype.Service;
import project.ood.healthcalculator.dao.UserDAO;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.service.factory.CRUDServiceImpl;

@Service
public class UserServiceImpl extends CRUDServiceImpl<User, UserDAO> implements UserService{

    public UserServiceImpl(UserDAO userDAO) {
        super(userDAO);
    }

    @Override
    public User retrieve(String name) {
        return super.getBaseDAO().findByUserName(name);
    }
}
