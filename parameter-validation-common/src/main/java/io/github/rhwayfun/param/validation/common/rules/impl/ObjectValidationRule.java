package io.github.rhwayfun.param.validation.common.rules.impl;

import io.github.rhwayfun.param.validation.common.annotation.ValidationRule;
import io.github.rhwayfun.param.validation.common.domain.ValidationResult;
import io.github.rhwayfun.param.validation.common.rules.ParamValidationRule;
import io.github.rhwayfun.param.validation.common.util.ValidationUtils;

/**
 * Created by rhwayfun on 2018/8/3.
 */
@ValidationRule
public class ObjectValidationRule implements ParamValidationRule {

    @Override
    public ValidationResult validate(Object param, Class<?> paramObjectType, String condition) throws Exception {
        return ValidationUtils.validateNonNull(param);
    }

    @Override
    public String name() {
        return Object.class.getName();
    }

}
