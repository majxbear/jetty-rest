package org.bigdata;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.servlet.ServletContainer;


public class JettyServer {

    public static void main(String[] args) throws Exception {
        int port = 8081;
        Server server = new Server(port);
        ServletHolder sh = new ServletHolder(ServletContainer.class);
        sh.setInitParameter("jersey.config.server.provider.packages",
                "org.bigdata.res,org.bigdata.exception," +
                        "org.bigdata.filter");

        //文件特性支持
        sh.setInitParameter("jersey.config.server.provider.classnames",
                "org.glassfish.jersey.media.multipart.MultiPartFeature");

        ServletContextHandler apiContext = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        apiContext.addServlet(sh, "/*");
        apiContext.setContextPath("/");


        HandlerList handlerList = new HandlerList();
        handlerList.addHandler(createWebappContext());
        handlerList.addHandler(apiContext);
        server.setHandler(handlerList);
        server.start();
    }

    private static WebAppContext createWebappContext() {
        WebAppContext context = new WebAppContext();
        context.setBaseResource(Resource.newClassPathResource("webapp"));
        context.setContextPath("/web");
        context.setWelcomeFiles(new String[]{"index.html"});
        return context;
    }

}

