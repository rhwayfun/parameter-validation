package io.github.rhwayfun.param.validation.common.rules.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.rhwayfun.param.validation.common.annotation.ValidationRule;
import io.github.rhwayfun.param.validation.common.domain.ValidationResult;
import io.github.rhwayfun.param.validation.common.rules.ParamValidationRule;
import io.github.rhwayfun.param.validation.common.util.ValidationUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by rhwayfun on 2018/8/6.
 */
@ValidationRule
public class MaxSizeValidationRule implements ParamValidationRule {

    @Override
    public ValidationResult validate(Object param, Class<?> paramObjectType, String condition)
            throws Exception {
        ValidationResult result = new ValidationResult(true);
        if (StringUtils.isBlank(condition)) {
            return result;
        }
        JSONObject jsonObject = JSON.parseObject(condition);
        Integer maxSize = jsonObject.getInteger("value");
        if (maxSize == null) {
            return result;
        }
        String msg = jsonObject.getString("msg");
        return ValidationUtils.validateSizeGte(param, maxSize, msg);
    }

    @Override
    public String name() {
        return MaxSizeValidationRule.class.getName();
    }

}
