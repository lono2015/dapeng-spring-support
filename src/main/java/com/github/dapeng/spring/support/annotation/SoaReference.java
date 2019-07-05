package com.github.dapeng.spring.support.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SoaReference {

    String version() default "";

    Class<?> classType();

}
