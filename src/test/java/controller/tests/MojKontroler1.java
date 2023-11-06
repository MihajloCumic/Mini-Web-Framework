package controller.tests;

import annotations.GET;
import annotations.POST;
import annotations.Path;

public class MojKontroler1 {
    public MojKontroler1(){}

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
}
