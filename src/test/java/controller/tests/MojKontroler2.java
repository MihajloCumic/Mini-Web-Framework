package controller.tests;

import annotations.Autowired;
import annotations.Controller;
import annotations.Qualifier;
import controller.tests.podpaket.InterfaceDependency;
import controller.tests.podpaket.podpodpaket.AbstractClassDependency;

@Controller
public class MojKontroler2 {
    @Autowired
    @Qualifier(value="imp")
    private InterfaceDependency interfaceDependency;
    @Autowired
    private AbstractClassDependency abstractClassDependency;

    public MojKontroler2(){}

    public void ispisi(){
        this.interfaceDependency.interfaceIspis();
    }
}
