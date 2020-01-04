package org.bigdata;

import org.bigdata.common.CommonServer;

public class UserServer {

    public static void main(String[] args) throws Exception {
        CommonServer server = new CommonServer(8989, "/api/v1.0",
                "org.bigdata.res2");
        server.start();
    }
}
