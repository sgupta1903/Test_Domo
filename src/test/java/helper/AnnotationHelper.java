package helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class AnnotationHelper {
    private AnnotationHelper() {
    }

    @SuppressWarnings("unchecked")
    public static <T extends Annotation> List<T> getAnnotationsOnMethod(Method testMethod, final Class<T> clazz) {
        if (testMethod == null) {
            return emptyList();
        }
        return Stream.of(testMethod.getAnnotationsByType(clazz))
                .map(clazz::cast)
                .collect(toList());
    }

    @SuppressWarnings("unchecked")
    public static <T extends Annotation> List<T> getAnnotationsOnMethodClass(Method testMethod, final Class<T> clazz) {
        if (testMethod == null) {
            return emptyList();
        }
        return Stream.of(testMethod.getDeclaringClass().getAnnotationsByType(clazz))
                .map(clazz::cast)
                .collect(toList());
    }

    @SuppressWarnings("unchecked")
    public static <T extends Annotation> List<T> getAnnotationsOnClass(Class testClass, final Class<T> clazz) {
        if (testClass == null) {
            return emptyList();
        }
        return Stream.of(testClass.getAnnotationsByType(clazz))
                .map(clazz::cast)
                .collect(toList());
    }
}
