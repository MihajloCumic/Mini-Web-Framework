package demonstracija.dependencies.implementations;

import annotations.Qualifier;
import annotations.Service;
import demonstracija.dependencies.specifications.Specification1;
import http.framework.request.Request;

import java.util.HashMap;

@Service
@Qualifier(value="imp1")
public class Implementation1 implements Specification1 {
    public Implementation1(){}

    @Override
    public String specification1Method(Request request) {
        HashMap<String, String> map = request.getParameters();
        String res = "";
        for(String key: map.keySet()){
            res += map.get(key) + ", ";
        }
        return res;
    }
}
