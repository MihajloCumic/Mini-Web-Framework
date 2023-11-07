package engine;

import engine.controller.ControllerContainer;

public class DependencyEngine {
    private final ControllerContainer controllerContainer;
    private static DependencyEngine instance;

    private DependencyEngine(){
        this.controllerContainer = ControllerContainer.getInstance();
    }

    public static synchronized DependencyEngine getInstance(){
        if(instance == null){
            instance = new DependencyEngine();
        }
        return instance;
    }
}
