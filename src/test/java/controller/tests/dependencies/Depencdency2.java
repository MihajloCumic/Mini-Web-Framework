package controller.tests.dependencies;

import annotations.Service;

@Service
public class Depencdency2 {
    public Depencdency2(){
        System.out.println("Inicijalizovan je Dependency2 @Service");
    }

    public void ispisi(){
        System.out.println("Ispisano iz Dependency2");
    }
}
