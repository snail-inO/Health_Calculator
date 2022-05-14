package project.ood.healthcalculator.service;

import project.ood.healthcalculator.entity.UserFoods;
import project.ood.healthcalculator.enums.SpecialIdEnum;
import project.ood.healthcalculator.service.CRUD.CRUDService;

public class ShareFoodDecoratorServiceImpl extends BaseDecoratorServiceImpl {
    public ShareFoodDecoratorServiceImpl(CRUDService<UserFoods> wrappee) {
        super(wrappee);
    }

    @Override
    public UserFoods create(Object o) {
        return (UserFoods) super.create(UserFoods.newBuilder().withUserFoods((UserFoods) o)
                .withUserFoodsId(SpecialIdEnum.NOT_USED.getId()).build());
    }

}
