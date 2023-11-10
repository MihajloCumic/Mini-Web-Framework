package demonstracija.dependencies.services;

import annotations.Autowired;
import annotations.Service;
import demonstracija.dependencies.components.Component1;

@Service
public class Service1 {
    @Autowired(verbose = true)
    private Component1 component1;
    public Service1(){}

 public String getData(){
        return "Service1" +  this.component1.findData();
 }

}
