package demonstracija.controllers;

import annotations.*;
import demonstracija.dependencies.services.Service1;
import demonstracija.dependencies.specifications.Specification1;
import http.framework.request.Request;

import java.util.HashMap;

@Controller
public class Controller1 {

    @Autowired(verbose = true)
    private Service1 service1;
    @Autowired(verbose = true)
    @Qualifier(value="imp1")
    private Specification1 specification1;

    public Controller1(){

    }

    @Path(path="/controller1")
    @GET
    public HashMap<String, Object> methodGET(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",this.service1.getData());
        return map;
    }

    @Path(path="/controller1")
    @POST
    public HashMap<String, Object> methodPOST(Request request){
        HashMap<String, Object> map = new HashMap<>();
        String params = this.specification1.specification1Method(request);
        map.put("allParams", params);
        return map;
    }

}
