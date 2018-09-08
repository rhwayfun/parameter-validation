package io.github.rhwayfun.param.validation.common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by rhwayfun on 2018/8/2.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ValidationRule {

    String name() default "";

}
