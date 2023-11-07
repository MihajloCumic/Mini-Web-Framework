package start;

import engine.controller.Controller;
import engine.controller.ControllerContainer;
import engine.controller.scanner.ControllerScanner;

public class ApplicationStart {
    public static void run(Class<?> clazz){
        System.out.println("Starting application...");
        String appPackageName = clazz.getPackageName();

        ControllerContainer controllerContainer = ControllerScanner.findControllers(appPackageName);
        for(Controller controller: controllerContainer.getControllers()){
            System.out.println(controller.getControllerType().getSimpleName());
        }

    }
}
