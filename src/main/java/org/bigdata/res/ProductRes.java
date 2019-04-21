package org.bigdata.res;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@Path("/product")
public class ProductRes {

    //资源信息，可获取处理请求的资源类和方法，在自定义注解中非常有用
    @Context
    ResourceInfo resourceInfo;

    //访问地址
    @Context
    UriInfo uriInfo;

    //请求头
    @Context
    HttpHeaders httpHeaders;

    //经常使用在日志记录注解中，获取响应的状态码
    @Context
    HttpServletResponse response;

    //使用频率最高的对象
    @Context
    HttpServletRequest request;

    //用户认证相关
    @Context
    SecurityContext sc;

    @Path("")
    @GET
    public String get() {
        System.out.println(resourceInfo.getResourceClass());
        System.out.println(resourceInfo.getResourceMethod());
        System.out.println(uriInfo.getAbsolutePath());
        System.out.println(uriInfo.getPath());
        System.out.println(request.getMethod());
        return "";
    }
}
