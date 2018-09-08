package io.github.rhwayfun.param.validation.spring;

import com.alibaba.fastjson.JSONObject;
import io.github.rhwayfun.param.validation.common.annotation.*;
import io.github.rhwayfun.param.validation.common.domain.ValidationResult;
import io.github.rhwayfun.param.validation.common.rules.impl.MaxSizeValidationRule;
import io.github.rhwayfun.param.validation.spring.wrapper.MethodValidationWrapper;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rhwayfun on 2018/8/2.
 */
public class ValidationDispatcherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationDispatcherService.class);

    private static final int RESERVE_LEN = 2;

    @Resource
    private ParamValidationService paramValidationService;

    public ValidationResult validateMethod(Object... args) {

        try {
            ValidationResult validationResult = new ValidationResult(true);
            if (ArrayUtils.isEmpty(args)) {
                return validationResult;
            }
            if (args.length < RESERVE_LEN) {
                return validationResult;
            }
            Object[] params = args;
            String methodName = params[1].toString();
            String key = params[0].getClass().getName() + "." + methodName;
            //method params
            List<Class<?>> methodParam = MethodValidationWrapper.getMethodParam(key);
            if (CollectionUtils.isEmpty(methodParam)) {
                return validationResult;
            }
            //method annotation
            Method method = MethodValidationWrapper.getMethod(key);
            ParamValidation paramValidation = method.getAnnotation(ParamValidation.class);
            if (paramValidation == null) {
                return validationResult;
            }
            //param index annotation map
            Map<Integer, Annotation> paramIndexAnnotationMap = getParamIndexAnnotationMap(method);
            for (int i = RESERVE_LEN; i < args.length; i++) {
                int paramIndex = i - RESERVE_LEN;
                if (shouldValidate(paramIndexAnnotationMap, paramIndex)) {
                    Map<Integer, Annotation> fieldAnnotationMap = getFieldAnnotationMap(params[i]);
                    if (!CollectionUtils.isEmpty(fieldAnnotationMap)) {
                        String condition = buildValidationCondition(fieldAnnotationMap);
                        validationResult = paramValidationService
                                .validate(params[i], methodParam.get(paramIndex),
                                        Arrays.asList(paramValidation.validateRules()), condition,
                                        paramValidation.failMsg());
                    } else {
                        validationResult = paramValidationService
                                .validate(params[i], methodParam.get(paramIndex),
                                        Arrays.asList(paramValidation.validateRules()),
                                        paramValidation.condition(), paramValidation.failMsg());
                    }
                }
                if (!validationResult.isSuccess()) {
                    return validationResult;
                }
            }
        } catch (Exception e) {
            LOGGER.error("param validate occurs error.", e);
        }

        return new ValidationResult(true);
    }

    private String buildValidationCondition(Map<Integer, Annotation> fieldAnnotationMap) {
        JSONObject object = new JSONObject();
        for (Map.Entry<Integer, Annotation> entry : fieldAnnotationMap.entrySet()) {
            Annotation annotation = entry.getValue();
            if (annotation.annotationType().equals(MaxSize.class)) {
                MaxSize maxSize = (MaxSize) annotation;
                int value = maxSize.value();
                String msg = maxSize.msg();
                object.put("name", MaxSizeValidationRule.class.getName());
                object.put("value", value);
                object.put("msg", msg);
            } else if (annotation.annotationType().equals(MinSize.class)) {

            }
        }
        return object.toJSONString();
    }

    private Map<Integer, Annotation> getFieldAnnotationMap(Object param) {
        Map<Integer, Annotation> fieldAnnotationMap = new HashMap<>();
        Field[] fields = param.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            for (Annotation annotation : field.getAnnotations()) {
                if (annotation.annotationType().isAnnotationPresent(FieldValidation.class)) {
                    fieldAnnotationMap.put(i, annotation);
                    break;
                }
            }
        }
        return fieldAnnotationMap;
    }

    private boolean shouldValidate(Map<Integer, Annotation> paramIndexAnnotationMap,
            int paramIndex) {
        if (CollectionUtils.isEmpty(paramIndexAnnotationMap)) {
            return true;
        }
        if (paramIndexAnnotationMap.get(paramIndex) instanceof NonValidation) {
            return false;
        }
        return true;
    }

    private Map<Integer, Annotation> getParamIndexAnnotationMap(Method method) {
        Map<Integer, Annotation> paramMap = new HashMap<>();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (!ArrayUtils.isEmpty(parameterAnnotations)) {
            for (int i = 0; i < parameterAnnotations.length; i++) {
                for (int j = 0; j < parameterAnnotations[i].length; j++) {
                    if (null != parameterAnnotations[i][j]) {
                        paramMap.put(i, parameterAnnotations[i][j]);
                    }
                }
            }
        }
        return paramMap;
    }

}
