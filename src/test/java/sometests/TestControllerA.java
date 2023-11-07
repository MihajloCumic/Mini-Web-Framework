package sometests;

import annotations.Controller;
import controller.tests.podpaket.podpodpaket.MojKontroler4;

public class TestControllerA {
    public static void main(String[] args) {
        MojKontroler4 mojKontroler4 = new MojKontroler4();
        mojKontroler4.provera();
        Class<?> annotation = Controller.class;
    }
}
