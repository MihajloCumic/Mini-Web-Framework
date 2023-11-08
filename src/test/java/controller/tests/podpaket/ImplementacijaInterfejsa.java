package controller.tests.podpaket;

import annotations.Autowired;
import annotations.Compoenent;
import annotations.Qualifier;
import controller.tests.Dependency3;

@Compoenent
@Qualifier(value="imp")
public class ImplementacijaInterfejsa implements InterfaceDependency{
    private String name = "IMplementacijaInterfejsa";
    @Autowired
    private Dependency3 dependency3;
    public ImplementacijaInterfejsa(){}
    @Override
    public void interfaceIspis() {
        System.out.println("Ispis iz " + this.name);
    }
}
