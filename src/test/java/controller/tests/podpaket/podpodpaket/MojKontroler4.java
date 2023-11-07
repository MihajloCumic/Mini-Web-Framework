package controller.tests.podpaket.podpodpaket;


import annotations.Autowired;
import annotations.Controller;
import controller.tests.Dependency3;
import controller.tests.dependencies.Depencdency2;


@Controller
public class MojKontroler4 {

    @Autowired
    private Dependency3 dependency3;
    @Autowired
    private Depencdency2 dependency2;

    public MojKontroler4(){
        System.out.println("Inicijhalizovao Kontroler4");
    }

    public void provera(){
        System.out.println("Ispis iz kontrolra4");
    }
}
