package http.server;

import http.framework.request.Header;
import http.framework.request.Helper;
import http.framework.request.Request;
import http.framework.request.enums.Method;
import http.framework.request.exeptions.RequestNotValidException;
import http.framework.response.JsonResponse;
import http.framework.response.Response;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerThread implements Runnable{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ServerThread(Socket socket){
        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            Request request = this.generateRequest();
            if(request == null){
                in.close();
                out.close();
                socket.close();
                return;
            }
            //Response example
//            Map<String, Object> responseMap = new HashMap<>();
//            responseMap.put("route_location", request.getLocation());
//            responseMap.put("route_method", request.getMethod().toString());
//            responseMap.put("parameters", request.getParameters());
//            Response response = new JsonResponse(responseMap);
            //Ovo iznad treba izmenititi

            Map<String, Object> responseMap = makeResponseMap(request, null);
            Response response = new JsonResponse(responseMap);

            out.println(response.render());

            in.close();
            out.close();
            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, Object> makeResponseMap(Request request, HashMap<String, Object> map){
        if(map == null){
            HashMap<String, Object> errorMap = new HashMap<>();
            errorMap.put("error", "Could not find endpoint.");
            return errorMap;
        }
        return map;
    }

    private Request generateRequest() throws IOException {
        String command = in.readLine();
        if(command == null){
            return null;
        }

        String[] actionRow = command.split(" ");
        Method method = Method.valueOf(actionRow[0]);
        String route = actionRow[1];
        Header header = new Header();
        HashMap<String, String> parameters = Helper.getParametersFromRoute(route);

        do {
            command = in.readLine();
            String[] headerRow = command.split(": ");
            if(headerRow.length == 2){
                header.add(headerRow[0], headerRow[1]);
            }
        }while(!command.trim().equals(""));

        if(method.equals(Method.POST)){

            int contentLength = Integer.parseInt(header.get("Content-Length"));
            char[] buff = new char[contentLength];
            in.read(buff, 0, contentLength);
            String parametersString = new String(buff);


            HashMap<String, String> postParameters = Helper.getParametersFromString(parametersString);

            for(String parameterName: postParameters.keySet()){
                parameters.put(parameterName, postParameters.get(parameterName));
            }
        }

        Request request = new Request(method, route, header, parameters);
        return request;
    }
}
