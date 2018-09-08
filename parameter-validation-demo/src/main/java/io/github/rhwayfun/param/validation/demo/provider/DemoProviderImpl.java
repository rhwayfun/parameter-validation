package io.github.rhwayfun.param.validation.demo.provider;

import io.github.rhwayfun.param.validation.common.annotation.ParamValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by rhwayfun on 2018/8/3.
 */
@Component
public class DemoProviderImpl implements DemoProvider {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoProviderImpl.class);

    @Override
    @ParamValidation(validateRules = "ParamListValidationRule")
    public String sayHello(DemoParam param) {
        return param.toString();
    }

    @Override
    @ParamValidation(validateRules = {"String"})
    public void sayHelloV2(String msg) {
        LOGGER.info("Receive msg: " + msg);
    }

}
