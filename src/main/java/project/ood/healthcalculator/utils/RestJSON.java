package project.ood.healthcalculator.utils;

import com.alibaba.fastjson.JSON;
import project.ood.healthcalculator.enums.RestEnum;

public class RestJSON {
    private boolean success;
    private RestEnum restEnum;
    private Object data;

    public RestJSON() {
        this.success = true;
        this.restEnum = RestEnum.SUCCESS;
    }

    public RestJSON(Object data) {
        this.success = true;
        this.restEnum = RestEnum.SUCCESS;
        this.data = JSON.toJSON(data);
    }

    public RestJSON(CustomException e) {
        this.restEnum = e.restEnum;
        this.success = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return restEnum.getRestCode();
    }

    public String getMsg() {
        return restEnum.getRestMsg();
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
