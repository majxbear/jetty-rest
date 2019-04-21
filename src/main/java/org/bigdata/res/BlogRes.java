package org.bigdata.res;

import org.bigdata.domain.Blog;
import org.bigdata.service.BlogService;

import javax.validation.constraints.Size;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/blog")
public class BlogRes {
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Blog get(
            @Size(min = 24, max = 24, message = "id必须为24位")
            @PathParam("id") String id) {
        //使用id获取blog
        Blog blog = BlogService.getById(id);
        return blog;
    }
}
