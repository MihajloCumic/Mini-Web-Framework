package ditests.controllers;


import annotations.Autowired;
import annotations.Controller;
import annotations.Qualifier;
import ditests.dependencies.ServiceDependency1;
import ditests.dependencies.specifications.InterfaceDependency1;

@Controller
public class MyController1 {
    private String name = "MyController1";

    @Autowired
    private ServiceDependency1 serviceDependency1;
    @Autowired
    @Qualifier(value="imp1")
    private InterfaceDependency1 interfaceDependency1;

    public MyController1(){
        System.out.println("Inicijalizovan je:: " + this.name);
    }
}
