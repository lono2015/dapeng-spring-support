package com.github.dapeng.spring.support.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service
@Inherited
@Documented
public @interface SoaService {

}
