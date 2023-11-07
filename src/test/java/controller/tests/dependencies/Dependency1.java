package controller.tests.dependencies;

import annotations.Autowired;
import annotations.Service;

@Service
public class Dependency1 {
    @Autowired
    private Depencdency2 depencdency2;
    public Dependency1(){
        System.out.println("Inicijalizovan je Dependency1 @Service");
    }

    public void ispisi(){
        this.depencdency2.ispisi();
    }
}
