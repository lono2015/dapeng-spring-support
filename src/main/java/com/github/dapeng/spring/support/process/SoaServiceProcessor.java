package com.github.dapeng.spring.support.process;

import com.github.dapeng.spring.SoaProcessorFactory;
import com.github.dapeng.spring.support.annotation.SoaService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

public class SoaServiceProcessor implements InitializingBean, ApplicationContextAware {

    private ConfigurableApplicationContext configurableApplicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String[] beanNamesForAnnotation = configurableApplicationContext.getBeanNamesForAnnotation(SoaService.class);
        for (String beanName : beanNamesForAnnotation) {
            String id = beanName + "_SoaProcessor";
            DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
            SoaProcessorFactory soaProcessorFactory = new SoaProcessorFactory(defaultListableBeanFactory.getBean(beanName), beanName);
            defaultListableBeanFactory.registerSingleton(id, soaProcessorFactory);
        }
    }
}
