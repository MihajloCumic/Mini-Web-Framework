package demonstracija.dependencies.implementations;

import annotations.Qualifier;
import demonstracija.dependencies.specifications.Specification2;

@Qualifier(value="imp2")
public class Implementation2 implements Specification2 {
    public Implementation2(){}
    @Override
    public void specification2Method() {

    }
}
