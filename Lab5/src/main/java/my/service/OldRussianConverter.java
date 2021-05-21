package main.java.my.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface OldRussianConverter {
    @WebMethod
    double convert(double num, Measures from, Measures to);
}

