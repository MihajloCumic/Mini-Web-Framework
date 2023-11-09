package ditests.controllers;


import annotations.*;
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

    public void testDependencies(){
        this.serviceDependency1.ispisi();
        this.interfaceDependency1.ispisi1();
    }

    @GET
    @Path(path = "/mycontroller1/metoda1")
    public void httpMethod1(){

    }

    @POST
    @Path(path="/mycontroller1/metoda2")
    public void httpMethod2(){}

}
