package ditests.controllers;


import annotations.*;
import ditests.dependencies.ServiceDependency1;
import ditests.dependencies.specifications.InterfaceDependency1;
import http.framework.request.Request;

import java.util.HashMap;

@Controller
public class MyController1 {
    private String name = "MyController1";

    @Autowired(verbose = true)
    private ServiceDependency1 serviceDependency1;
    @Autowired(verbose = true)
    @Qualifier(value="imp1")
    private InterfaceDependency1 interfaceDependency1;

    public MyController1(){
        //System.out.println("Inicijalizovan je:: " + this.name);
    }

    public void testDependencies(){
        this.serviceDependency1.ispisi();
        this.interfaceDependency1.ispisi1();
    }

    @GET
    @Path(path = "/test")
    public HashMap<String, Object> httpMethod1(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("nasao", "putanju /test");
        return map;
    }

    //Baca exeption za istu putanju i metodu
//    @GET
//    @Path(path = "/test")
//    public HashMap<String, Object> httpMethod11(){
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("nasao", "putanju /test");
//        return map;
//    }

    @POST
    @Path(path="/test")
    public HashMap<String, Object> httpMethod2(Request request){
        HashMap<String, Object> map = new HashMap<>();
        map.put("kontoler", this.name);
        map.put("params", request.getParameters());
        return map;
    }

    public InterfaceDependency1 getInterfaceDependency1() {
        return interfaceDependency1;
    }
}
