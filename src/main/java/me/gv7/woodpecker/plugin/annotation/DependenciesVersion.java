package me.gv7.woodpecker.plugin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DependenciesVersion {
	String value();

//	public static class Utils {
//		public static String getDependenciesVersion(AnnotatedElement annotated) {
//			DependenciesVersion deps = annotated.getAnnotation(DependenciesVersion.class);
//			if (deps != null && deps.value() != null) {
//				return deps.value();
//			} else {
//				return null;
//			}
//		}

//	}
}
