package controller.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent.getSession().getServletContext().getAttribute("loggedUsers");

        String login = (String) httpSessionEvent.getSession().getAttribute("login");
        loggedUsers.remove(login);
        HttpSession session = httpSessionEvent.getSession();

        session.setAttribute("login", null);
        session.setAttribute("role", null);

        httpSessionEvent.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
    }
}
