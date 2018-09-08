package io.github.rhwayfun.param.validation.common.annotation;

import io.github.rhwayfun.param.validation.common.handler.ValidationFailedHandler;
import io.github.rhwayfun.param.validation.common.handler.impl.DefaultValidationFailedHandler;
import io.github.rhwayfun.param.validation.common.rules.ParamValidationRule;

import java.lang.annotation.*;

/**
 * Created by rhwayfun on 2018/8/2.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamValidation {

    /**
     * Exception handler if validate failed. The default exception handler is
     *
     * @see ValidationFailedHandler
     */
    Class<? extends ValidationFailedHandler> exceptionHandler() default DefaultValidationFailedHandler.class;

    /**
     * Self validation rules. if specified, it will include these rules for validation.
     * As well, the basic validation rule <code>ObjectValidationRule</code> executes automatically.
     *
     * @see ParamValidationRule
     */
    String[] validateRules() default {};

    /**
     * Specially used for field validation, such as <code>MaxSizeValidationRule</code>. By
     * annotation <code>@MaxSize</code>, it will validate whether field size is match condition.
     *
     * @see ParamValidationRule
     */
    String condition() default "";

    /**
     * Failed message if validate failed.
     */
    String failMsg() default "";

}
