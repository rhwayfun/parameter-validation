package io.github.rhwayfun.param.validation.common.annotation;

import java.lang.annotation.*;

/**
 * Created by rhwayfun on 2018/8/2.
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NonValidation {
}
