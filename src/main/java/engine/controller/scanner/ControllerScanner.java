package engine.controller.scanner;

import annotations.Controller;
import engine.controller.ControllerContainer;
import exeptions.FrameWorkExeptions;
import org.reflections.Reflections;


import java.util.Set;

public class ControllerScanner {
    public static ControllerContainer findControllers(String appPakcageName) throws FrameWorkExeptions {
        ControllerContainer controllerContainer = ControllerContainer.getInstance();

        Reflections reflections = new Reflections(appPakcageName);
        Set<Class<?>> controllerClasses = reflections.getTypesAnnotatedWith(Controller.class);

        for(Class<?> controller: controllerClasses){
            controllerContainer.createController(controller);
        }

        return controllerContainer;
    }
}
