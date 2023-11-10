package engine.controller;

import exeptions.FrameWorkExeptions;
import http.framework.request.Request;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ControllerContainer {
    private static ControllerContainer instance;
    private final List<Controller> controllers;

    private ControllerContainer(){
        this.controllers = new ArrayList<>();
    }

    public static synchronized ControllerContainer getInstance(){
        if(instance == null){
            instance = new ControllerContainer();
        }
        return instance;
    }

    public void createController(Class<?> controllerType) throws FrameWorkExeptions {
        if(!isControllerUnique(controllerType)) return;
        Controller controller = Controller.create(controllerType);
        this.controllers.add(controller);
    }

    private boolean isControllerUnique(Class<?> controllerType){
        for(Controller controller: this.controllers){
            if(controller.getControllerType().getName().equals(controllerType.getName())){
                return false;
            }
        }
        return true;

    }

    public HashMap<String, Object> findAndCallMethodWithPath(String path, Request request) throws InvocationTargetException, IllegalAccessException {
        for(Controller controller: this.controllers){
            if(controller.getPathToControllerMethod().get(path) != null){
                Method method = controller.getPathToControllerMethod().get(path);
                Object controllerInstance =  controller.getController();
                int paramNumber = method.getParameterCount();

                if(paramNumber == 0) return (HashMap<String, Object>) method.invoke(controllerInstance);
                if(paramNumber == 1) return (HashMap<String, Object>) method.invoke(controllerInstance, request);
                return null;

            }
        }

        return null;
    }

    public List<Controller> getControllers() {
        return controllers;
    }
}
