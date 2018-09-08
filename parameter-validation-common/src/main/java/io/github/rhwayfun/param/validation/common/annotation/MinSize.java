package io.github.rhwayfun.param.validation.common.annotation;

import java.lang.annotation.*;

/**
 * Created by rhwayfun on 2018/8/6.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@FieldValidation
public @interface MinSize {

    /**
     * Min size for validation.
     */
    int value();

    /**
     * If min size condition is not matched, the msg will return.
     */
    String msg() default "";

}
