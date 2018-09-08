package io.github.rhwayfun.param.validation.demo.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by rhwayfun on 2018/8/3.
 */
@Component
@EnableScheduling
public class DemoProviderTestTask {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoProviderTestTask.class);

    @Resource
    private DemoProvider demoProvider;

    @Scheduled(fixedDelay = 10000)
    public void execute() {
        DemoParam param = new DemoParam();
        //param.setCityId(1);
        param.setOrdrId(1L);
        param.setOrderIds(new ArrayList<>());
        LOGGER.info("scheduled task result: " + demoProvider.sayHello(param));
    }

}
