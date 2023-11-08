package scanner;


import annotations.Controller;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Set;

public abstract class PackageScanner {
    private String packageName;

    public PackageScanner(String packageName){
        this.packageName = packageName;
    }

    public abstract Set<Class<?>> findAnnotatedClassesInPackage();
    protected Set<Class<?>> findAnnotatedClassesInPackage(Class<? extends Annotation> annotation){
        Reflections reflections = new Reflections(this.packageName);
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(annotation);
        return annotatedClasses;
    }

}
