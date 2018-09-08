package io.github.rhwayfun.param.validation.common.domain;

/**
 * Created by rhwayfun on 2018/8/2.
 */
public class ValidationFailedResult extends ValidationResult {

    private String failCode;
    private String failReason;

    public ValidationFailedResult(boolean isSuccess) {
        super(isSuccess);
    }

    public String getFailCode() {
        return failCode;
    }

    public void setFailCode(String failCode) {
        this.failCode = failCode;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    @Override
    public String toString() {
        return "failCode='" + failCode + "'" + ", failReason='" + failReason + "', " + super.toString();
    }
}
