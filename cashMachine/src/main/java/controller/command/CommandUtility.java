package controller.command;

import model.entity.enums.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtility {

    public static void unlogUser(HttpServletRequest request, String login) {
        HashSet<String> loggedUsers = (HashSet<String>)
                request.getSession().getServletContext().getAttribute("loggedUsers");

        loggedUsers.remove(login);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);

        HttpSession session = request.getSession();

        session.setAttribute("login", null);
        session.setAttribute("role", null);
        session.setAttribute("userId", null);
    }

    static boolean checkUserIsLogged(HttpServletRequest request, String login, Role role, int id){
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext().getAttribute("loggedUsers");

        if(loggedUsers.stream().anyMatch(login::equals)){
            return true;
        }
        loggedUsers.add(login);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);

        HttpSession session = request.getSession();

        session.setAttribute("login", login);
        session.setAttribute("role", role);
        session.setAttribute("userId", id);
        return false;
    }
}
