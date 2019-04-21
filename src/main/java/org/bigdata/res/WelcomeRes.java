package org.bigdata.res;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;


@Path("")
@WebService
public class WelcomeRes {
    @Path("")
    @GET
    public String sayHello(@Context HttpServletRequest request) {
        return "hello, jetty!";
    }
}
