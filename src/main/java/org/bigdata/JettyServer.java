package org.bigdata;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;


public class JettyServer {

    public static void main(String[] args) throws Exception {
        int port = 8081;
        Server server = new Server(port);
        ServletHolder sh = new ServletHolder(ServletContainer.class);
        sh.setInitParameter("jersey.config.server.provider.packages",
                "org.bigdata.res,org.bigdata.exception");

        //文件特性支持
        sh.setInitParameter("jersey.config.server.provider.classnames",
                "org.glassfish.jersey.media.multipart.MultiPartFeature");

        ServletContextHandler apiContext = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        apiContext.addServlet(sh, "/*");
        apiContext.setContextPath("/");

        HandlerList handlerList = new HandlerList();
        handlerList.addHandler(apiContext);
        server.setHandler(handlerList);
        server.start();
    }

}

