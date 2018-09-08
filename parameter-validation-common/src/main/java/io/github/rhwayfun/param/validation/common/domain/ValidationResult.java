package io.github.rhwayfun.param.validation.common.domain;

/**
 * Created by rhwayfun on 2018/8/2.
 */
public class ValidationResult {

    private boolean isSuccess;
    private String msg;

    public ValidationResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "isSuccess=" + isSuccess + ", msg='" + msg + "'";
    }
}
