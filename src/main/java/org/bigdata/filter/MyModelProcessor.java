package org.bigdata.filter;

import org.glassfish.jersey.server.model.ModelProcessor;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.server.model.ResourceMethod;
import org.glassfish.jersey.server.model.ResourceModel;

import javax.ws.rs.core.Configuration;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.Set;

@Provider
public class MyModelProcessor implements ModelProcessor {
    @Override
    public ResourceModel processResourceModel(ResourceModel resourceModel, Configuration configuration) {
        //获取类上注册的资源
        List<Resource> resources = resourceModel.getResources();
        for (Resource r : resources) {
            //注册路径
            String path = r.getPath();
            //注册的类，至少有一个处理类
            // 注：可以有多个类注册一个路径，但路径下的方法注解不能重复
            Set<Class<?>> handlerClasses = r.getHandlerClasses();
            for (Class clazz : handlerClasses) {
                System.out.println(clazz.getName());
            }
            //jersey中路径注册如果为空字符串，则与"/"等效
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            System.out.println("Class resource path: " + path);
            //获取方法上注册的子资源
            List<Resource> childResources = r.getChildResources();
            for (Resource cr : childResources) {
                String childPath = cr.getPath();
                if (!childPath.startsWith("/")) {
                    childPath = "/" + childPath;
                }
                List<ResourceMethod> methods = cr.getAllMethods();
                for (ResourceMethod m : methods) {
                    System.out.println("Child method resource path: " + childPath
                            + " ,method: " + m.getHttpMethod());
                }
            }
            System.out.println("*************************************");
        }
        return resourceModel;
    }

    @Override
    public ResourceModel processSubResource(ResourceModel resourceModel, Configuration configuration) {
        return resourceModel;
    }
}
