package project.ood.healthcalculator.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.ood.healthcalculator.entity.Nutrient;


public interface NutrientDAO extends CrudRepository<Nutrient, Long> {

}
