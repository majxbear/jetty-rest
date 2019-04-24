package org.bigdata.filter;


import org.apache.commons.lang3.StringUtils;
import org.bigdata.domain.SimpleMsg;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Priority(Priorities.AUTHENTICATION)
@Provider
@UserAuth
public class UserAuthFilter implements ContainerRequestFilter {

    public void filter(ContainerRequestContext req) throws IOException {
        String user = req.getHeaderString("user");
        String token = req.getHeaderString("pass");
        if (StringUtils.isBlank(user) || StringUtils.isBlank(token)) {
            Response resp = Response.status(Response.Status.FORBIDDEN)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(new SimpleMsg("no permission."))
                    .build();
            req.abortWith(resp);
        }
        //模拟简单的用户认证逻辑，实际应用中具体实现
        if (!"admin".equals(user) || !"admin".equals(token)) {
            Response resp = Response.status(Response.Status.FORBIDDEN)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(new SimpleMsg("no permission."))
                    .build();
            req.abortWith(resp);
        }
    }
}
