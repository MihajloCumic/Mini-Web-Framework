package controller.tests;

import engine.controller.Controller;
import engine.controller.ControllerContainer;

public class TestController {
    public static void main(String[] args) {
        ControllerContainer controllerContainer = ControllerContainer.getInstance();
        controllerContainer.createController(MojKontroler1.class);
        controllerContainer.createController(MojKontroler2.class);
        controllerContainer.createController(MojKontroler1.class);
        System.out.println(controllerContainer.getControllers().size());
        for (Controller controller: controllerContainer.getControllers()){
            System.out.println(controller.getControllerType().getTypeName());
            System.out.println(controller.getPathToControllerMethod());
        }
    }
}
