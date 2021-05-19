package main.java.my.service;

import javax.xml.ws.Endpoint;

public class WebServiceMain {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/ws", new MyWebService());
    }
}
