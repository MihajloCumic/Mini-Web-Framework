package controller.tests;

import annotations.*;
import controller.tests.dependencies.Dependency1;


@Controller
public class MojKontroler1 {

    private String name = "mojkontroler1";
    @Autowired
    private Dependency1 dependency1;
    @Autowired
    private Dependency3 dependency3;

    public MojKontroler1(){}

    public void ispisi1(){
        this.dependency1.ispisi();
    }

    @Path(path="/metoda1/MojKontroler1")
    @GET
    public void metoda1MojKontroler1(){}

    @Path(path="/metoda2/MojKontroler1")
    @POST
    public void metoda2MojKontroler1(){}

    @Path(path="/metoda3/MojKontroler1")
    public void metoda3MojKontroler1(){}


    @POST
    public void metoda4MojKontroler1(){}

    public Dependency1 getDependency1() {
        return dependency1;
    }

    public Dependency3 getDependency3() {
        return dependency3;
    }
}
