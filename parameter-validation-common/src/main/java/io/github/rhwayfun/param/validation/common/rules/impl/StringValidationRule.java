package io.github.rhwayfun.param.validation.common.rules.impl;

import io.github.rhwayfun.param.validation.common.annotation.ValidationRule;
import io.github.rhwayfun.param.validation.common.domain.ValidationResult;
import io.github.rhwayfun.param.validation.common.rules.ParamValidationRule;
import io.github.rhwayfun.param.validation.common.util.ValidationUtils;

/**
 * Created by rhwayfun on 2018/8/6.
 */
@ValidationRule
public class StringValidationRule implements ParamValidationRule {

    @Override
    public ValidationResult validate(Object param, Class<?> paramObjectType, String condition)
            throws Exception {
        return ValidationUtils.validateStringNonBlank(param);
    }

    @Override
    public String name() {
        return "String";
    }

}
