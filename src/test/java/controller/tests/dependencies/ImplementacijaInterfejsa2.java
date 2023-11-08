package controller.tests.dependencies;

import annotations.Compoenent;
import annotations.Qualifier;

@Compoenent
@Qualifier(value="imp2")
public class ImplementacijaInterfejsa2 implements Interfejs2{
    private String name = "ImplementacijaInterfejsa2";

    public ImplementacijaInterfejsa2(){

    }

    @Override
    public void ispisi2() {
        System.out.println("Ispis iz: " + this.name);
    }
}
