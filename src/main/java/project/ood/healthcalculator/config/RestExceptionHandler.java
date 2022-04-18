package project.ood.healthcalculator.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.ood.healthcalculator.enums.RestEnum;
import project.ood.healthcalculator.utils.CustomException;
import project.ood.healthcalculator.utils.RestJSON;

@RestControllerAdvice("project.ood.healthcalculator.controller")
public class RestExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public RestJSON customExceptionHandler(CustomException e) {
        LOGGER.error("Service Exception: {}", e.getRestEnum().getRestMsg());

        return new RestJSON(e);
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public RestJSON nullPointerExceptionHandler(NullPointerException e) {
        LOGGER.error("Null Pointer Exception: {}", e);

        return new RestJSON(new CustomException(RestEnum.BAD_REQUEST, e));
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestJSON exceptionHandler(Exception e) {
        LOGGER.error("Internal Error: {}", e);

        return new RestJSON(new CustomException(RestEnum.INTERNAL_SERVER_ERROR, e));
    }
}
