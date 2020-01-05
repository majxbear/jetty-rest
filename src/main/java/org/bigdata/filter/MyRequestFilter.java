package org.bigdata.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@PreMatching
public class MyRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext req) throws IOException {
        String path = req.getUriInfo().getPath();
        //记录请求信息:请求方法、路径
        System.out.println(req.getMethod());
        System.out.println(path);
        // 根据条件进行转发
//        if (path.contains("/api/v1.0/xx")) {
//            req.setRequestUri(URI.create("/xx"));
//        }
    }
}
