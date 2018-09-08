package io.github.rhwayfun.param.validation.spring.wrapper;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by rhwayfun on 2018/8/2.
 */
public class MethodValidationWrapper {

    /**
     * Method collection with @ParamValidation
     */
    private static Map<String, Method> methodMap = new ConcurrentHashMap<>();

    /**
     * Method parameters collection with @ParamValidation
     */
    private static Map<String, List<Class<?>>> methodParamMap = new ConcurrentHashMap<>();

    public static Method getMethod(String mk) {
        return methodMap.get(mk);
    }

    public static List<Class<?>> getMethodParam(String mk) {
        return methodParamMap.get(mk);
    }

    public static void addMethodSignature(String mk, Method m) {
        methodMap.put(mk, m);
    }

    public static void addMethodParam(String mk, List<Class<?>> parameterTypes) {
        methodParamMap.put(mk, parameterTypes);
    }

}
