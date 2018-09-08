package io.github.rhwayfun.param.validation.spring.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * Created by rhwayfun on 2018/8/2.
 */
public class SpringUtils {

    private static ApplicationContext ctx;

    public static <T> T getBean(Class<T> t) {
        return ctx.getBean(t);
    }

    public static void setCtx(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    public static ApplicationContext getCtx() {
        return ctx;
    }
}
