/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplewebserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Kowalewski
 */
public class MyHttpHandler
    implements HttpHandler
{

    @Override
    public void handle(HttpExchange httpExchange) throws IOException
    {

        System.out.println("handle()");
        String requestParamValue = null;

        if ("GET".equals(httpExchange.getRequestMethod()))
        {
            requestParamValue = handleGetRequest(httpExchange);
        }
        else if ("POST".equals(httpExchange))
        {

            //requestParamValue = handlePostRequest(httpExchange);
        }

        handleResponse(httpExchange,
                       requestParamValue);

    }

    private String handleGetRequest(HttpExchange httpExchange)
    {

        System.out.println("handleGetRequest()");
        return httpExchange.
            getRequestURI().
            toString().
            split("\\?")[1]
            .split("=")[1];
    }

    private void handleResponse(HttpExchange httpExchange,
                                String requestParamValue) throws IOException
    {
        System.out.println("handleResponse  ");
        OutputStream outputStream = httpExchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html>").
            append("<body>").
            append("<h1>").
            append("Hello ").
            append(requestParamValue).
            append("</h1>").
            append("</body>").
            append("</html>");

        // encode HTML content 
        //String htmlResponse = StringEscapeUtils.escapeHtml4(htmlBuilder.
        String htmlResponse = htmlBuilder.
            toString();

        // this line is a must
        httpExchange.sendResponseHeaders(200,
                                         htmlResponse.length());
        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();

    }
}
