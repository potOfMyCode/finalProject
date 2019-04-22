package controller.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LogOut implements Command{
    private final static Logger LOGGER = Logger.getLogger(LogOut.class.getSimpleName());
    @Override
    public String execute(HttpServletRequest request) {
        Optional<Object> login = Optional.ofNullable(request.getSession().getAttribute("login"));

        login.ifPresent(e -> CommandUtility.unlogUser(request, e.toString()));

        LOGGER.info("User logout successful with login: " + request.getSession().getAttribute("login"));

        return "redirect:login";
    }
}
