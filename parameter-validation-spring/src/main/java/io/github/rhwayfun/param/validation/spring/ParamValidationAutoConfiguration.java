package io.github.rhwayfun.param.validation.spring;

import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Created by rhwayfun on 2018/8/2.
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = {"io.github.rhwayfun"})
public class ParamValidationAutoConfiguration {

    @Bean
    public ValidationDispatcherService validationDispatcherService() {
        return new ValidationDispatcherService();
    }

    @Bean
    public ParamValidationService paramValidationService() {
        return new ParamValidationService();
    }

    @Bean
    public ParamValidationBeanPostProcessor paramValidationBeanPostProcessor() {
        return new ParamValidationBeanPostProcessor();
    }

    @Bean
    public ParamValidationAspect paramValidationAspect() {
        return Aspects.aspectOf(ParamValidationAspect.class);
    }

    @Bean
    public Validator validator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

}
