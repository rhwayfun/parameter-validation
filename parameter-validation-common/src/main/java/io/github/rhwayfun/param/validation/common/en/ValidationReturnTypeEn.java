package io.github.rhwayfun.param.validation.common.en;

/**
 * Created by rhwayfun on 2018/8/2.
 */
public enum  ValidationReturnTypeEn {
    PARAM_ERROR("1", "参数错误");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    ValidationReturnTypeEn(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
