package project.ood.healthcalculator.service.builder;

public interface EntityDirectorService {
    static <T extends EntityBuilderService> T createBuilder(Class<T> c) throws InstantiationException, IllegalAccessException {
        return c.newInstance();
    }
}
