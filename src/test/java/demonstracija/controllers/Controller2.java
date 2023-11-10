package demonstracija.controllers;

import annotations.*;
import demonstracija.dependencies.components.Component1;
import demonstracija.dependencies.services.Service1;
import http.framework.request.Request;

import java.util.HashMap;

@Controller
public class Controller2 {
    @Autowired(verbose = true)
    private Service1 service1;
    @Autowired(verbose = true)
    private Component1 component1;
    public Controller2(){

    }

    @Path(path = "/controller2")
    @GET
    public HashMap<String, Object> methodGET(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",this.service1.getData());
        return map;
    }
    @Path(path = "/controller2")
    @POST
    public HashMap<String, Object> methodPOST(Request request){
        return null;
    }
}
