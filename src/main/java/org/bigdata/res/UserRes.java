package org.bigdata.res;

import org.bigdata.domain.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/user")
public class UserRes {
    //获取用户列表
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public User getAll(@QueryParam("name") String name,
                       @QueryParam("age") @DefaultValue("10") int age) {
        System.out.println(name);
        System.out.println(age);
        User user = new User(name, age);
        return user;
    }

    //创建用户
    @PUT
    @Path("")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String add(@FormParam("name") String name,
                      @FormParam("age") int age) {
        return "name: " + name + " , age: " + age;
    }


    //删除用户
    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") String id) {
        return "";
    }

    //修改用户
    @POST
    @Path("/{id}")
    public String update(@PathParam("id") String id) {
        return "";
    }

    //获取用户详细信息
    @GET
    @Path("/{id}")
    public String get(@PathParam("id") String id) {
        return "";
    }

    //判断用户是否存在
    @HEAD
    @Path("/{id}")
    public String getInfo(@PathParam("id") String id) {
        return "";
    }


}
