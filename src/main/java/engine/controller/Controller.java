package engine.controller;


import annotations.Path;
import exeptions.FrameWorkExeptions;
import exeptions.messages.PathAlreadyExistsMessage;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private final Class<?> controllerType;
    private final Map<String, Method> pathToControllerMethod;
    private  Object controller;

    private Controller(Class<?> controllerType) throws FrameWorkExeptions {
        this.controllerType = controllerType;
        this.pathToControllerMethod = new HashMap<>();
        try {
            this.controller = controllerType.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        this.mapPathsToControllerMethods();
    }

    public  static Controller create(Class<?> controllerType) throws FrameWorkExeptions {
        return new Controller(controllerType);
    }

    private void mapPathsToControllerMethods() throws FrameWorkExeptions {
        Method[] methods = this.controllerType.getDeclaredMethods();

        for(Method method: methods){
            Path pathAnnotation = method.getAnnotation(Path.class);
            if(pathAnnotation == null) continue;
            String httpMethodName = this.AllowedHTTPMethodAnnotationName(method);
            if(httpMethodName == null){
                System.out.println("\tThere must be http method annotation with path annotation.");
                continue;
            }
            String path = pathAnnotation.path();
            String methodAndPath = httpMethodName + ":" + path;

            if(this.pathToControllerMethod.get(methodAndPath) != null){
                Method methodWithPath = this.pathToControllerMethod.get(methodAndPath);
                PathAlreadyExistsMessage message = new PathAlreadyExistsMessage(methodAndPath, this.controllerType.getSimpleName(), methodWithPath.getName());
                throw new FrameWorkExeptions(message.toString());
            }
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

    public Object getController() {
        return controller;
    }
}
