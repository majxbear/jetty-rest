package org.bigdata.res2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/test")
public class TestRes {
    @GET
    public Response test() {
        return Response.ok("package res2 TestRes").build();
    }
}
