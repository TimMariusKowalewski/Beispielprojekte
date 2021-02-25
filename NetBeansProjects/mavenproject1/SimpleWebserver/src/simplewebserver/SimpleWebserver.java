/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplewebserver;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kowalewski
 */
public class SimpleWebserver
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        
        HttpServer server = null;
        try
        {
            server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
        }
        catch (IOException ex)
        {
            Logger.getLogger(SimpleWebserver.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        server.createContext("/test1",
                             new MyHttpHandler());
        server.createContext("/test2",
                             new MyJSONHandler());
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
        server.setExecutor(threadPoolExecutor);
        
        server.start();
        
        //logger.info(" Server started on port 8001");
    }

}
