package main.java.my.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ServiceInterface {
    @WebMethod
    public String getHelloString(String name);
}
