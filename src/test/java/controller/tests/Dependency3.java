package controller.tests;

import annotations.Compoenent;

@Compoenent
public class Dependency3 {
    public Dependency3(){
        System.out.println("Inicijalizovan je Dependency3 @Component");
    }

    public void ispisi(){
        System.out.println("Ispis iz Dependency3.");
    }
}
