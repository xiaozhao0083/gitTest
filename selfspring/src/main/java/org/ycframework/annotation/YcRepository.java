package org.ycframework.annotation;

import org.springframework.stereotype.Repository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-27 19:07
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@YcComponent
public @interface YcRepository {
    String value() default "";
}
