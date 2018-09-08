package io.github.rhwayfun.param.validation.spring.wrapper;

import io.github.rhwayfun.param.validation.common.rules.ParamValidationRule;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rhwayfun on 2018/8/2.
 */
public class ParamValidationRulesWrapper {

    private static Map<String, ParamValidationRule> ruleMap = new HashMap<>();

    public static ParamValidationRule getParamValidationRule(String ruleName) {
        if (StringUtils.isBlank(ruleName)) {
            return ruleMap.get(Object.class.getName());
        }
        ParamValidationRule rule = ruleMap.get(ruleName);
        return rule != null ? rule : ruleMap.get(Object.class.getName());
    }

    public static void addParamValidationRule(ParamValidationRule rule) {
        ruleMap.put(rule.name(), rule);
    }

}
