package org.bigdata.filter;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * 日志记录注解
 */
@Provider
@UserLog
public class UserLogFilter implements ContainerResponseFilter {
    private static Logger logger = Logger.getLogger(UserLogFilter.class);

    //使用注解注入Request对象
    @Context
    private HttpServletRequest request;

    @Override
    public void filter(ContainerRequestContext req, ContainerResponseContext resp) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append(request.getRemoteHost()).append(" ")
                .append(request.getMethod()).append(" ")
                .append(request.getPathInfo()).append(" ")
                .append(request.getContentLength()).append(" ")
                .append(request.getHeader("Referrer")).append(" ")
                .append(request.getHeader("User-Agent")).append(" ")
                .append(resp.getStatus());
        logger.info(builder.toString());
    }
}
