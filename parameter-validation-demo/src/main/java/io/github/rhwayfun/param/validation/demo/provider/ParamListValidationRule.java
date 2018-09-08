package io.github.rhwayfun.param.validation.demo.provider;

import io.github.rhwayfun.param.validation.common.annotation.ValidationRule;
import io.github.rhwayfun.param.validation.common.domain.ValidationResult;
import io.github.rhwayfun.param.validation.common.rules.ParamValidationRule;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rhwayfun on 2018/8/6.
 */
@ValidationRule
public class ParamListValidationRule implements ParamValidationRule {

    @Override
    public ValidationResult validate(Object param, Class<?> paramObjectType, String condition)
            throws Exception {
        if (param instanceof DemoParam) {
            List<Long> orders = queryByOrderIds(((DemoParam) param).getOrderIds());
            if (CollectionUtils.isEmpty(orders)) {
                ValidationResult result = new ValidationResult(false);
                result.setMsg("订单id列表查询为空！");
                return result;
            }
        }
        return new ValidationResult(true);
    }

    private List<Long> queryByOrderIds(List<Long> orderIds) {
        List<Long> l = new ArrayList<>();
        l.add(1000L);
        return l;
    }

    @Override
    public String name() {
        return "ParamListValidationRule";
    }
}
