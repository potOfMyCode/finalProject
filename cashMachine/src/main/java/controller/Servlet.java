package controller;

import controller.command.Command;
import model.entity.enums.Role;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(Servlet.class.getSimpleName());

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        servletConfig.getServletContext().setAttribute("loggedUsers", new HashSet<String>());

        servletConfig.getServletContext().setAttribute("role", Role.UNKNOWN);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Command> commands = (Map<String, Command>) request.getSession().getAttribute("commands");

        String path = request.getRequestURI();
        path = path.replaceAll(".*/app/", "");

        Command command =commands.getOrDefault(path, (r)->"/login.jsp");

        String page = command.execute(request);

        if (page.contains("redirect")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
