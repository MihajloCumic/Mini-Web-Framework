package http.framework.response;

import http.framework.request.Header;

public abstract class Response {
    protected Header header;

    public Response(){
        this.header = new Header();
    }

    public abstract String render();

}
