package io.github.rhwayfun.param.validation.spring;

import com.alibaba.fastjson.JSON;
import io.github.rhwayfun.param.validation.common.annotation.ParamValidation;
import io.github.rhwayfun.param.validation.common.domain.ValidationResult;
import io.github.rhwayfun.param.validation.common.handler.ValidationFailedHandler;
import io.github.rhwayfun.param.validation.spring.wrapper.ParamValidationFailedHandlerWrapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * Created by rhwayfun on 2018/8/2.
 */
@Aspect
// todo 为什么加上这个注解就可以注入？
@Configurable
public class ParamValidationAspect {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(ParamValidationAspect.class);

    @Resource
    private ValidationDispatcherService validationDispatcherService;

    @Around("execution(* *(..)) && @annotation(paramValidation)")
    public Object around(ProceedingJoinPoint pjp, ParamValidation paramValidation) throws Throwable {
        ValidationResult result = validate(pjp);
        if (!result.isSuccess()) {
            // execute failed handler
            Signature signature = pjp.getSignature();
            MethodSignature msgi = (MethodSignature) signature;
            Method method = msgi.getMethod();
            ValidationFailedHandler failedHandler = ParamValidationFailedHandlerWrapper
                    .getValidationFailedHandler(paramValidation.exceptionHandler().getName());
            return failedHandler.validateFailed(result.getMsg(), method.getReturnType(), pjp.getArgs());
        }
        return pjp.proceed();
    }

    private ValidationResult validate(ProceedingJoinPoint pjp) throws Throwable {
        Signature signature = pjp.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("@ParamValidation can only be used in methods!");
        }
        MethodSignature msig = (MethodSignature) signature;
        Object target = pjp.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        if (currentMethod.isAnnotationPresent(ParamValidation.class)) {
            Object[] args = pjp.getArgs();
            Object[] params = new Object[args.length + 2];
            //类名全路径
            params[0] = pjp.getTarget();
            //方法名
            params[1] = currentMethod.getName();
            System.arraycopy(args, 0, params, 2, params.length - 2);
            ValidationResult result = validationDispatcherService.validateMethod(params);
            if (!result.isSuccess()) {
                LOGGER.warn(pjp.getTarget().getClass().getSimpleName() + "." +
                        currentMethod.getName() +
                        "|validationResult=false,param=" +
                        JSON.toJSONString(args));
                return result;
            }
        }
        return new ValidationResult(true);
    }

}
