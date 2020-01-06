package org.bigdata;

import org.bigdata.common.CommonServer;

public class UserServer {

    public static void main(String[] args) throws Exception {
        CommonServer server = new CommonServer(8081, "/",
                null,"org.bigdata.filter.MyModelProcessor");
        server.start();
    }
}
