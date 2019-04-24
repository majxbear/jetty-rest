package org.bigdata.res;

import org.bigdata.filter.UserAuth;
import org.bigdata.filter.UserLog;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Path("/computer")
@UserLog
public class ComputerRes {
    @UserAuth
    @Path("")
    @GET
    public Response get() {
        return Response.ok("computers").build();
    }
}
