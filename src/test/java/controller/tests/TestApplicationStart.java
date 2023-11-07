package controller.tests;

import engine.controller.Controller;
import engine.controller.ControllerContainer;
import start.ApplicationStart;

public class TestApplicationStart {
    public static void main(String[] args) {
        ApplicationStart.run(TestApplicationStart.class);
        ControllerContainer controllerContainer = ControllerContainer.getInstance();
        MojKontroler1 mojKontroler1 = null;
        for(Controller controller: controllerContainer.getControllers()){
            if(controller.getControllerType().getSimpleName().equals("MojKontroler1")){
                mojKontroler1 = (MojKontroler1) controller.getController();
            }
        }
        if(mojKontroler1 == null) System.out.println("Nije ga nasaso");
        else mojKontroler1.ispisi1();
    }
}
