package demonstracija.dependencies.implementations;

import annotations.Qualifier;
import demonstracija.dependencies.specifications.Specification2;

@Qualifier(value="imp3")
public class Implementation3 implements Specification2 {
    public Implementation3(){}
    @Override
    public void specification2Method() {

    }
}
