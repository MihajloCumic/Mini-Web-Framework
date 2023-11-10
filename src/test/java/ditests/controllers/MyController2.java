package ditests.controllers;

import annotations.Autowired;
import annotations.Controller;
import annotations.Qualifier;
import ditests.dependencies.specifications.InterfaceDependency1;

@Controller
public class MyController2 {
    private String name = "MyController2";
    @Autowired(verbose = true)
    @Qualifier(value="imp1")
    private InterfaceDependency1 interfaceDependency1;

    public MyController2(){
        //System.out.println("Inicijalizovan je:: " + this.name);
    }

    public InterfaceDependency1 getInterfaceDependency1() {
        return interfaceDependency1;
    }
}
