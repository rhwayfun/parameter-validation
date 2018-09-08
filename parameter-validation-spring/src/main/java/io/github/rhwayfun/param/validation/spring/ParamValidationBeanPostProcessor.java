package io.github.rhwayfun.param.validation.spring;

import io.github.rhwayfun.param.validation.common.annotation.ParamValidation;
import io.github.rhwayfun.param.validation.common.handler.ValidationFailedHandler;
import io.github.rhwayfun.param.validation.common.rules.ParamValidationRule;
import io.github.rhwayfun.param.validation.spring.wrapper.MethodValidationWrapper;
import io.github.rhwayfun.param.validation.spring.wrapper.ParamValidationFailedHandlerWrapper;
import io.github.rhwayfun.param.validation.spring.util.SpringUtils;
import io.github.rhwayfun.param.validation.spring.wrapper.ParamValidationRulesWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by rhwayfun on 2018/8/2.
 */
public class ParamValidationBeanPostProcessor implements BeanPostProcessor, SmartInitializingSingleton, ApplicationContextAware {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ValidationFailedHandler) {
            ParamValidationFailedHandlerWrapper.addHandler(bean.getClass().getName(), (ValidationFailedHandler) bean);
        }
        if (bean instanceof ParamValidationRule) {
            ParamValidationRule rule = (ParamValidationRule) bean;
            ParamValidationRulesWrapper.addParamValidationRule(rule);
        }
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        if (methods != null) {
            for (Method method : methods) {
                ParamValidation validation = AnnotationUtils.findAnnotation(method, ParamValidation.class);
                if (validation != null) {
                    String mk = bean.getClass().getName() + "." + method.getName();
                    MethodValidationWrapper.addMethodSignature(mk, method);
                    MethodValidationWrapper.addMethodParam(mk, Arrays.asList(method.getParameterTypes()));
                }
            }
        }
        return bean;
    }

    @Override
    public void afterSingletonsInstantiated() {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.setCtx(applicationContext);
    }

}
