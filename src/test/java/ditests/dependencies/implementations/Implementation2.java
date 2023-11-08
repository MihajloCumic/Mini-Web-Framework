package ditests.dependencies.implementations;

import annotations.Compoenent;
import annotations.Qualifier;
import ditests.dependencies.specifications.InterfaceDependency2;

@Compoenent
@Qualifier(value="imp2")
public class Implementation2 implements InterfaceDependency2 {
    private String name = "Implementation2";

    public Implementation2(){
        System.out.println("Inicijalizovan je:: " + this.name);
    }
    @Override
    public void ispisi2() {
        System.out.println("Ispis iz Implementation1.");
    }
}
