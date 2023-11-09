package http.framework.request;

import java.util.HashMap;
import java.util.Set;

public class Header {
    protected HashMap<String, String> headers;

    public Header(){
        this.headers = new HashMap<>();
    }

    public void add(String name, String value){
        this.headers.put(name, value);
    }

    public String get(String name){
        return this.headers.get(name);
    }

    public Set<String> getKeys(){
        return this.headers.keySet();
    }

    @Override
    public String toString() {
        return this.headers.toString();
    }
}
