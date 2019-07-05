package com.github.dapeng.spring.support.process;

import com.github.dapeng.core.SoaConnectionPool;
import com.github.dapeng.core.SoaConnectionPoolFactory;
import com.github.dapeng.spring.support.annotation.SoaReference;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ServiceLoader;

/**
 * @author wwx
 * @date 2019-03-20
 */
public class SoaReferenceProcessor extends AnnotationInjectedBeanPostProcessor<SoaReference> {

    @Override
    protected Object doGetInjectedBean(SoaReference reference, Object bean, String beanName, Class injectedType, InjectionMetadata.InjectedElement injectedElement) throws Exception {
        Object obj = null;
        if (injectedType.isInterface()) {
            String version = reference.version();
            Class<?> clazz = reference.classType();
            if (injectedType.isAssignableFrom(clazz)) {
                if (StringUtils.isEmpty(version)) {
                    obj = clazz.newInstance();
                } else {
                    obj = clazz.getConstructor(String.class).newInstance(version);
                }
            } else {
                Assert.state(false, String.format("%s is not interface %s", clazz.getName(), injectedType.getName()));
            }
        } else {
            Assert.state(false, String.format("%s is not interface", injectedType.getName()));
        }
        return obj;
    }

    @Override
    protected String buildInjectedObjectCacheKey(SoaReference reference, Object bean, String beanName, Class injectedType, InjectionMetadata.InjectedElement injectedElement) {
        String version = reference.version();
        String key = injectedType.getName();
        if (!StringUtils.isEmpty(version)) {
            key += ":" + reference.version();
        }
        return key;
    }

}
