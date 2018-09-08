package io.github.rhwayfun.param.validation.common.annotation;

import java.lang.annotation.*;

/**
 * Created by rhwayfun on 2018/8/6.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@FieldValidation
public @interface MaxSize {

    /**
     * Max size for validation.
     */
    int value();

    /**
     * If max size condition is not matched, the msg will tell specific failed msg.
     */
    String msg() default "";

}
