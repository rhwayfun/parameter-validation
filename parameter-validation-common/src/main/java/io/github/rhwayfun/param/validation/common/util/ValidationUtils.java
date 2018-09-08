package io.github.rhwayfun.param.validation.common.util;

import io.github.rhwayfun.param.validation.common.annotation.NonValidation;
import io.github.rhwayfun.param.validation.common.domain.ValidationResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

/**
 * Created by rhwayfun on 2018/8/3.
 */
public class ValidationUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationUtils.class);

    public static ValidationResult validateNonNull(Object param) throws Exception {
        ValidationResult validationResult = new ValidationResult(false);
        if (Objects.isNull(param)) {
            validationResult.setMsg("param is null!");
            return validationResult;
        }
        for (Field field : param.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            NonValidation nonValidation = field.getAnnotation(NonValidation.class);
            if (nonValidation != null) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("skip non validation field:{}!", field.getName());
                }
                continue;
            }
            if (field.get(param) == null) {
                validationResult.setMsg(field.getName() + " is null!");
                return validationResult;
            }
        }
        return new ValidationResult(true);
    }

    public static ValidationResult validateStringNonBlank(Object param) {
        if (param instanceof String) {
            String s = (String) param;
            if (StringUtils.isBlank(s)) {
                ValidationResult result = new ValidationResult(false);
                result.setMsg("字符串为空");
                return result;
            }
        }
        return new ValidationResult(true);
    }

    public static ValidationResult validateSizeGte(Object param, Integer maxSize, String msg)
            throws Exception {
        for (Field field : param.getClass().getDeclaredFields()) {
            if (field.getType().isAssignableFrom(List.class)) {
                field.setAccessible(true);
                Object o = field.get(param);
                if (((List) o).size() > maxSize) {
                    ValidationResult result = new ValidationResult(false);
                    result.setMsg(msg);
                    return result;
                }
            }
        }
        return new ValidationResult(true);
    }

}
