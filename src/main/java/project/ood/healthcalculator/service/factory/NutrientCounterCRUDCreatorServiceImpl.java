package project.ood.healthcalculator.service.factory;

import org.springframework.stereotype.Service;
import project.ood.healthcalculator.entity.NutrientCounter;
import project.ood.healthcalculator.entity.UserRecipe;
import project.ood.healthcalculator.service.CRUD.NutrientCounterService;
import project.ood.healthcalculator.service.CRUD.NutrientCounterServiceImpl;

import java.util.List;

@Service
public class NutrientCounterCRUDCreatorServiceImpl extends CRUDCreatorService<NutrientCounter, NutrientCounterServiceImpl>
        implements NutrientCounterService {

    public NutrientCounterCRUDCreatorServiceImpl(NutrientCounterServiceImpl nutrientCounterService) {
        super(nutrientCounterService);
    }

    @Override
    public List<NutrientCounter> retrieveAll(UserRecipe userRecipe) {
        return super.getCRUDService().retrieveAll(userRecipe);
    }
}
