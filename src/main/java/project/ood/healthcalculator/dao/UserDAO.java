package project.ood.healthcalculator.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.ood.healthcalculator.entity.User;


public interface UserDAO extends CrudRepository<User, Long> {
    User findByUserName(String uname);
}
