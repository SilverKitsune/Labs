package main.java.my.service;

import javax.jws.WebService;

@WebService(endpointInterface = "main.java.my.service.ServiceInterface")
public class MyWebService implements ServiceInterface {
    @Override
    public String getHelloString(String name) {
        return "Hello, " + name + "!";
    }
}