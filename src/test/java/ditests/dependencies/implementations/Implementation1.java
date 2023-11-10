package ditests.dependencies.implementations;

import annotations.Bean;
import annotations.Compoenent;
import annotations.Qualifier;
import annotations.Service;
import annotations.enums.BeanScope;
import ditests.dependencies.specifications.InterfaceDependency1;

@Bean(scope = BeanScope.SINGLETON)
@Qualifier(value="imp1")
public class Implementation1 implements InterfaceDependency1 {
    private String name = "Implementation1";

    public Implementation1(){
        //System.out.println("Inicijalizovan je:: " + this.name);
    }
    @Override
    public void ispisi1() {
        System.out.println("Ispis iz Implementation1.");
    }
}
