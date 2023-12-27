package me.gv7.woodpecker.plugin.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

public class AnnotationUtils {
    public static <T extends Annotation> T getAnnotation(AnnotatedElement element, Class<T> annotationClass) {
        if (element.isAnnotationPresent(annotationClass)){
            return element.getAnnotation(annotationClass);
        }
        return null;
    }

    public static <T extends Annotation> Object getAnnotationValue(AnnotatedElement element, Class<T> annotationClass, String propertyName) {
        T annotation = getAnnotation(element, annotationClass);
        if (annotation != null) {
            try {
//                String version = (String) annotation.annotationType().getMethod(propertyName).invoke(annotation);
//                return version.split(",");
                return annotation.annotationType().getMethod(propertyName).invoke(annotation);
            } catch (Exception e) {
                // Handle reflection exceptions
                e.printStackTrace();
            }
        }
        return null;
    }
}
