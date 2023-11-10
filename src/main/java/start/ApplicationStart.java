package start;

import dependencycontainer.DependencyContainer;
import diengine.DIEngine;
import engine.controller.Controller;
import engine.controller.ControllerContainer;
import engine.controller.scanner.ControllerScanner;
import http.server.Server;
import scanner.PackageScanner;
import scanner.implementations.QualifierScanner;

import java.lang.reflect.InvocationTargetException;

public class ApplicationStart {
    public static void run(Class<?> clazz){
        System.out.println("Starting application...");
        String appPackageName = clazz.getPackageName();
        ControllerContainer controllerContainer = ControllerScanner.findControllers(appPackageName);

        DependencyContainer dependencyContainer = DependencyContainer.getInstance(appPackageName);
        DIEngine diEngine = DIEngine.getInstance(dependencyContainer);
        try {
            diEngine.injectDependencies();
        } catch (InvocationTargetException |  NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Server.startServer();



    }
}
