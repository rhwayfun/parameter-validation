package io.github.rhwayfun.param.validation.spring.wrapper;

import io.github.rhwayfun.param.validation.common.handler.ValidationFailedHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by rhwayfun on 2018/8/3.
 */
public class ParamValidationFailedHandlerWrapper {

    private static Map<String, ValidationFailedHandler> cachedValidationHandler = new ConcurrentHashMap<>();

    public static void addHandler(String clazz, ValidationFailedHandler handler) {
        cachedValidationHandler.put(clazz, handler);
    }

    public static ValidationFailedHandler getValidationFailedHandler(String clazz) {
        return cachedValidationHandler.get(clazz);
    }

}
