package org.bigdata.res;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author majx
 * @date 2019/4/21 0021
 */
@Path("/book")
public class Book {

    @Path("")
    @GET
    public Response get() throws Exception {

        boolean exist = false;
        if (!exist) {
            //如果书目不存在
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("指定的书目不存在")
                    .build();
        } else {
            return Response.ok("书目信息")
                    .build();
        }
//        throw new Exception("指定的书目不存在");
    }
}
