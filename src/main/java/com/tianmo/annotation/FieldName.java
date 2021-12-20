package com.tianmo.annotation;
import java.lang.annotation.ElementType;

@java.lang.annotation.Target({ElementType.FIELD, ElementType.TYPE})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Documented
public @interface FieldName {
    String value() default "";
}
