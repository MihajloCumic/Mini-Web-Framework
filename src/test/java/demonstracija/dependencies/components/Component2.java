package demonstracija.dependencies.components;

import annotations.Bean;
import annotations.enums.BeanScope;

@Bean(scope=BeanScope.PROTOTYPE)
public class Component2 {
    public Component2(){}
}
