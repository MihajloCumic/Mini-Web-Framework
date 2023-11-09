package ditests.controllers;

import annotations.Controller;

@Controller
public class MyController2 {
    private String name = "MyController2";

    public MyController2(){
        System.out.println("Inicijalizovan je:: " + this.name);
    }


}
