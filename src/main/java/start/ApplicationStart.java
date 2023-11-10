package start;

import dependencycontainer.DependencyContainer;
import diengine.DIEngine;
import engine.controller.ControllerContainer;
import engine.controller.scanner.ControllerScanner;
import exeptions.FrameWorkExeptions;
import http.server.Server;


import java.lang.reflect.InvocationTargetException;

public class ApplicationStart {
    public static void run(Class<?> clazz){
        System.out.println("Starting application...");
        String appPackageName = clazz.getPackageName();
        try {
            ControllerContainer controllerContainer = ControllerScanner.findControllers(appPackageName);
            DependencyContainer dependencyContainer = DependencyContainer.getInstance(appPackageName);
            DIEngine diEngine = DIEngine.getInstance(dependencyContainer);
            diEngine.injectDependencies();
            Server.startServer();
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException | FrameWorkExeptions e) {
            e.printStackTrace();
        }

    }
}
