package controller.tests;

import controller.tests.podpaket.podpodpaket.MojKontroler4;
import engine.controller.Controller;
import engine.controller.ControllerContainer;
import start.ApplicationStart;

public class TestApplicationStart {
    public static void main(String[] args) {
        ApplicationStart.run(TestApplicationStart.class);
        ControllerContainer controllerContainer = ControllerContainer.getInstance();
        MojKontroler1 mojKontroler1 = null;
        MojKontroler4 mojKontroler4 = null;
        for(Controller controller: controllerContainer.getControllers()){
            if(controller.getControllerType().getSimpleName().equals("MojKontroler1")){
                mojKontroler1 = (MojKontroler1) controller.getController();
                continue;
            }
            if(controller.getControllerType().getSimpleName().equals("MojKontroler4")){
                mojKontroler4 = (MojKontroler4) controller.getController();
                continue;
            }

        }
        if(mojKontroler1 == null || mojKontroler4 == null) System.out.println("Nije ga nasaso");
        else{
            mojKontroler1.ispisi1();
            mojKontroler4.provera();
            System.out.println("Servis: ");
            System.out.println(mojKontroler1.getDependency1().getDepencdency2());
            System.out.println(mojKontroler4.getDependency2());
            System.out.println("Komponenta:");
            System.out.println(mojKontroler1.getDependency3());
            System.out.println(mojKontroler4.getDependency3());
        }
    }
}
