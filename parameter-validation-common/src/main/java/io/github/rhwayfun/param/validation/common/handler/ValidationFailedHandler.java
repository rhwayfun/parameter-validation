package io.github.rhwayfun.param.validation.common.handler;

import io.github.rhwayfun.param.validation.common.domain.ValidationFailedResult;
import io.github.rhwayfun.param.validation.common.en.ValidationReturnTypeEn;

/**
 * Created by rhwayfun on 2018/8/2.
 */
public interface ValidationFailedHandler {

    /**
     * doSomething if validate failed.
     *
     * @param msg validate failed recommend message
     * @param returnType return type if failed
     * @param args validate arguments
     * @return Object
     */
    default Object validateFailed(String msg, Class returnType, Object... args) {
        ValidationFailedResult result = new ValidationFailedResult(false);
        result.setSuccess(false);
        result.setFailCode(ValidationReturnTypeEn.PARAM_ERROR.getCode());
        result.setFailReason(ValidationReturnTypeEn.PARAM_ERROR.getDesc());
        result.setMsg(msg);
        throw new IllegalArgumentException(result.toString());
    }

}
