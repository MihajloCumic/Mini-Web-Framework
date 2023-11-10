package demonstracija.dependencies.services;

import annotations.Bean;
import annotations.enums.BeanScope;

@Bean(scope= BeanScope.SINGLETON)
public class Service2 {
    public Service2(){}
}
