package org.bigdata.res;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/book")
public class BookRes {

    @Path("")
    @GET
    public Response get() throws Exception {
        throw new Exception("指定的书目不存在");
    }
}
