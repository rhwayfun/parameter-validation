package io.github.rhwayfun.param.validation.common.rules;

import io.github.rhwayfun.param.validation.common.domain.ValidationResult;

/**
 * Created by rhwayfun on 2018/8/3.
 */
public interface ParamValidationRule {

    ValidationResult validate(Object param, Class<?> paramObjectType, String condition) throws Exception;

    String name();

}
