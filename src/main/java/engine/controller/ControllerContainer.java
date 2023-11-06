package engine.controller;

import java.util.ArrayList;
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

    public void createController(Class<?> controllerType){
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

    public List<Controller> getControllers() {
        return controllers;
    }
}
