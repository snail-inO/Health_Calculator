package project.ood.healthcalculator.utils;

import project.ood.healthcalculator.enums.RestEnum;
import project.ood.healthcalculator.enums.RestInterface;

public class CustomException extends RuntimeException{

    protected RestEnum restEnum;

    public CustomException() {
        super();
    }

    public CustomException(RestEnum restEnum) {
        super(restEnum.getRestCode());
        this.restEnum = restEnum;
    }

    public CustomException(RestEnum restEnum, Throwable cause) {
        super(restEnum.getRestCode(), cause);
        this.restEnum = restEnum;
    }

    public RestEnum getRestEnum() {
        return restEnum;
    }

    public void setRestEnum(RestEnum restEnum) {
        this.restEnum = restEnum;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
