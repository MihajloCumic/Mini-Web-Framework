package controller.tests.dependencies;


import annotations.Qualifier;
import annotations.Service;

@Service
@Qualifier(value="imp1")
public class ImplementacijaInterfejsa1 implements Interfejs1{
    private String name = "ImplementacijaInterfejsa1";

    public ImplementacijaInterfejsa1(){

    }

    @Override
    public void ispisi1() {
        System.out.println("Ispis iz: " + this.name);
    }
}
