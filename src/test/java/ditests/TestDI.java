package ditests;

import ditests.controllers.MyController1;
import ditests.controllers.MyController2;
import ditests.controllers.MyController3;
import engine.controller.Controller;
import engine.controller.ControllerContainer;
import start.ApplicationStart;

public class TestDI {
    public static void main(String[] args) {
        ApplicationStart.run(TestDI.class);
        System.out.println("PROVERA KONTROLERA I DEPENDENCIA************************");
        ControllerContainer controllerContainer = ControllerContainer.getInstance();
        MyController1 myController1 = null;
        MyController2 myController2 = null;
        MyController3 myController3 = null;
        for(Controller controller: controllerContainer.getControllers()){
            System.out.println("\t->"+controller.getControllerType().getSimpleName());
            System.out.println("\t\t->"+controller.getPathToControllerMethod());
            if(controller.getControllerType().getSimpleName().equals("MyController1")){
                myController1 = (MyController1) controller.getController();
            }
            if(controller.getControllerType().getSimpleName().equals("MyController2")){
                myController2 = (MyController2) controller.getController();
            }
            if(controller.getControllerType().getSimpleName().equals("MyController3")){
                myController3 = (MyController3) controller.getController();
            }
        }
        if(myController1 == null || myController2 == null || myController3 == null){
            System.out.println("Missing some controlelr.");
            return;
        }
        System.out.println("DEPENDENCY TEST****************************\n\t-> MyController1:");
        myController1.testDependencies();
        System.out.println(myController1.getInterfaceDependency1());
        System.out.println(myController2.getInterfaceDependency1());



    }
}
