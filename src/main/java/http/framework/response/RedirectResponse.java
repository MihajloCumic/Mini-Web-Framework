package http.framework.response;

public class RedirectResponse extends Response{
    private String url;
    public RedirectResponse(String url){
        this.url = url;
        this.header.add("location", url);
    }
    @Override
    public String render() {
        StringBuilder responseContext = new StringBuilder();

        responseContext.append("HTTP/1.1 301 redirect\n");
        for(String key: this.header.getKeys()){
            responseContext.append(key).append(":").append(this.header.get(key)).append("\n");
        }
        responseContext.append("\n");
        return responseContext.toString();
    }
}
