package io.github.rhwayfun.param.validation.spring;

import com.alibaba.fastjson.JSONObject;
import io.github.rhwayfun.param.validation.common.domain.ValidationResult;
import io.github.rhwayfun.param.validation.spring.wrapper.ParamValidationRulesWrapper;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * Created by rhwayfun on 2018/8/2.
 */
public class ParamValidationService {

    @Resource
    private Validator validator;

    public ValidationResult validate(Object param, Class<?> paramObjectType,
            List<String> validateRules, String condition, String failMsg) throws Exception {
        // 基础校验
        ValidationResult validate = ParamValidationRulesWrapper
                .getParamValidationRule(paramObjectType.getName())
                .validate(param, paramObjectType, condition);
        if (!validate.isSuccess()) {
            return validate;
        }
        // 自定义校验--指定校验规则
        if (!CollectionUtils.isEmpty(validateRules)) {
            for (String validateRule : validateRules) {
                ValidationResult result = getValidationResult(param, paramObjectType, condition,
                        failMsg, validateRule);
                if (!result.isSuccess()) {
                    return result;
                }
            }
        }
        // 自定义校验--字段注解
        if (!StringUtils.isEmpty(condition)) {
            JSONObject object = JSONObject.parseObject(condition);
            String name = object.getString("name");
            return getValidationResult(param, paramObjectType, condition, failMsg, name);
        }
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(param);
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            ValidationResult validationResult = new ValidationResult(false);
            validationResult.setMsg(constraintViolations.iterator().next().getMessage());
            return validationResult;
        }
        return new ValidationResult(true);
    }

    private ValidationResult getValidationResult(Object param, Class<?> paramObjectType,
            String condition, String failMsg, String validateRule) throws Exception {
        if (!StringUtils.isEmpty(validateRule)) {
            ValidationResult result = ParamValidationRulesWrapper
                    .getParamValidationRule(validateRule)
                    .validate(param, paramObjectType, condition);
            if (!result.isSuccess()) {
                if (!StringUtils.isEmpty(failMsg)) {
                    result.setMsg(failMsg);
                }
                return result;
            }
        }
        return new ValidationResult(true);
    }

}
