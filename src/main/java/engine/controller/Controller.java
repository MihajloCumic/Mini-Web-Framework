package engine.controller;


import annotations.Path;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private final Class<?> controllerType;
    private final Map<String, Method> pathToControllerMethod;

    private Controller(Class<?> controllerType){
        this.controllerType = controllerType;
        this.pathToControllerMethod = new HashMap<>();
        this.mapPathsToControllerMethods();
    }

    public  static Controller create(Class<?> controllerType){
        return new Controller(controllerType);
    }

    private void mapPathsToControllerMethods(){
        Method[] methods = this.controllerType.getDeclaredMethods();

        for(Method method: methods){
            System.out.println(method.getName() + "****************");
            Path pathAnnotation = method.getAnnotation(Path.class);
            if(pathAnnotation == null) continue;
            String httpMethodName = this.AllowedHTTPMethodAnnotationName(method);
            if(httpMethodName == null){
                System.out.println("\tThere must be http method annotation with path annotation.");
                continue;
            }
            String path = pathAnnotation.path();
            this.pathToControllerMethod.put(httpMethodName + ":" + path, method);

        }
    }

    private String AllowedHTTPMethodAnnotationName(Method method){
        Annotation[] annotations = method.getDeclaredAnnotations();
        for(Annotation annotation: annotations){
            for(HTTPMethod httpMethod: HTTPMethod.values()){
                String annotationSimpleName = annotation.annotationType().getSimpleName();
                if(httpMethod.name().equalsIgnoreCase(annotationSimpleName)){
                    return annotationSimpleName;
                }
            }

        }
        return null;
    }

    public Class<?> getControllerType() {
        return controllerType;
    }

    public Map<String, Method> getPathToControllerMethod() {
        return pathToControllerMethod;
    }
}
