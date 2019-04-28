package org.bigdata.res;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/app")
public class AppRes {

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        //使用JSON模拟数据
        JSONArray apps = new JSONArray();
        JSONObject app1 = new JSONObject();
        app1.put("id", 100001);
        app1.put("name", "News");
        app1.put("desc", "News app");

        JSONObject app2 = new JSONObject();
        app2.put("id", 100002);
        app2.put("name", "Sports ");
        app2.put("desc", "Sports app");

        apps.add(app1);
        apps.add(app2);

        JSONObject resp = new JSONObject();
        resp.put("apps", apps);
        resp.put("total", 100);

        return Response.ok(JSON.toJSONString(resp, SerializerFeature.PrettyFormat)).build();
    }
}
