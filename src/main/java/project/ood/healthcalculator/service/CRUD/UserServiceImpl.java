package project.ood.healthcalculator.service.CRUD;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import project.ood.healthcalculator.dao.UserDAO;
import project.ood.healthcalculator.entity.User;

@Service
public class UserServiceImpl extends CRUDServiceImpl<User, UserDAO> implements UserService {

    public UserServiceImpl(UserDAO userDAO) {
        super(userDAO);
    }

    @Override
    public User retrieve(String name) {
        return getBaseDAO().findByUserName(name);
    }
}
