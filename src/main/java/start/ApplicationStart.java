package start;

import dependencycontainer.DependencyContainer;
import diengine.DIEngine;
import engine.controller.Controller;
import engine.controller.ControllerContainer;
import engine.controller.scanner.ControllerScanner;
import scanner.PackageScanner;
import scanner.implementations.QualifierScanner;

import java.lang.reflect.InvocationTargetException;

public class ApplicationStart {
    public static void run(Class<?> clazz){
        System.out.println("Starting application...");
        String appPackageName = clazz.getPackageName();
        ControllerContainer controllerContainer = ControllerScanner.findControllers(appPackageName);


        System.out.println("Ispis pronadjenih kontrolera:");
        for(Controller controller: controllerContainer.getControllers()){
            System.out.println("\t->"+controller.getControllerType().getSimpleName());
        }

        System.out.println("Ipis pronafjenih implementacija:");
        DependencyContainer dependencyContainer = DependencyContainer.getInstance(appPackageName);


        DIEngine diEngine = DIEngine.getInstance(dependencyContainer);
        try {
            diEngine.injectDependencies();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
