package controller.tests.dependencies;

import annotations.Service;

@Service
public class Dependency1 {
    public Dependency1(){
        System.out.println("Inicijalizovan je Dependency1 @Service");
    }
}
