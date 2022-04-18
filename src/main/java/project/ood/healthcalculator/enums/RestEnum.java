package project.ood.healthcalculator.enums;

public enum RestEnum implements RestInterface{
    SUCCESS("200","OK"),
    BAD_REQUEST("400", "Bad Request"),
    UNAUTHORIZED("401", "Unauthorized"),
    NOT_FOUND("404", "Not Found"),
    METHOD_NOT_ALLOWED("405", "Method Not Allowed"),
    INTERNAL_SERVER_ERROR("500", "Internal Server Error"),
    SERVICE_UNAVAILABLE("503", "Service Unavailable");

    private final String restCode;
    private final String restMsg;

    RestEnum(String restCode, String restMsg) {
        this.restCode = restCode;
        this.restMsg = restMsg;
    }

    @Override
    public String getRestCode() {
        return restCode;
    }

    @Override
    public String getRestMsg() {
        return restMsg;
    }
}
