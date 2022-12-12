package lesson7.server;

import java.io.IOException;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Handler extends AbstractHandler {
    @Override
    public void handle(String target, Request jettyRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request);
        if ("/service".equals(target)) {
            final String nameRq = request.getParameter("name");
            response.getWriter().write("Hello " + nameRq);
        }
        jettyRequest.setHandled(true);
    }
}