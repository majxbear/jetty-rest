package org.bigdata.common;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class CommonServer {
    /**
     * 默认注册到Jersey容器的资源包
     */
    private String defaultPackageName = "org.bigdata.res," +
            "org.bigdata.filter," +
            "org.bigdata.exception";

    private Server server;

    class Application extends ResourceConfig {
        /**
         * @param packagesNames 要注册到Jersey容器的资源包，包之间以英文逗号隔开，
         *                      如org.bigdata.res,org.bigdata.res
         * @param classnames    要注册到Jersey容器的资源类，类使用完整类名，类之间以英文逗号隔开,
         *                      如org.bigdata.res.AppRes,org.bigdata.res2.UserRes
         */
        Application(String packagesNames, String classnames) {
            //默认注册工程下的res包
            packages(defaultPackageName);
            //默认注册文件上传下载支持类
            register(MultiPartFeature.class);
            if (StringUtils.isNotBlank(packagesNames)) {
                packages(packagesNames);
            }
            if (StringUtils.isNotBlank(classnames)) {
                for (String name : classnames.split(",")) {
                    try {
                        Class<?> clazz = Class.forName(name);
                        register(clazz);
                    } catch (ClassNotFoundException e) {
                        //如果找不到注册的类，则跳过不予注册
                    }
                }
            }
        }
    }

    public CommonServer(int port, String contextPath) {
        this.server = getServer(port, contextPath, null, null);
    }

    public CommonServer(int port, String contextPath, String packagesNames) {
        this.server = getServer(port, contextPath, packagesNames, null);
    }

    /**
     * 创建服务器
     *
     * @param port          服务端口
     * @param contextPath   应用域路径
     * @param packagesNames 要注册到Jersey容器的资源包
     * @param classnames    要注册到Jersey容器的资源类
     */
    public CommonServer(int port, String contextPath, String packagesNames, String classnames) {
        this.server = getServer(port, contextPath, packagesNames, classnames);
    }

    private Server getServer(int port, String contextPath, String packagesNames, String classnames) {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.addConnector(connector);
        ServletContextHandler contextHandler = new ServletContextHandler();
        Application application = new Application(packagesNames, classnames);
        ServletContainer container = new ServletContainer(application);
        ServletHolder holder = new ServletHolder(container);

        contextHandler.addServlet(holder, "/*");
        contextHandler.setContextPath(contextPath);
        server.setHandler(contextHandler);

        HandlerList handlerList = new HandlerList();
        handlerList.addHandler(contextHandler);
        server.setHandler(handlerList);

        return server;
    }

    public void start() throws Exception {
        this.server.start();
    }

}
