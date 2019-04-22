package controller.command;

import model.entity.Worker;
import model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Login implements Command{
    private final static Logger LOGGER = Logger.getLogger(Login.class.getSimpleName());
    private Map<String, String> pages = new HashMap<>();
    private UserService userService;

    public Login(UserService userService) {
        this.userService = userService;

        pages.put("login", "/login.jsp");
        pages.put("CASHIER", "redirect:cashier");
        pages.put("HIGHCASHIER", "redirect:highcashier");
        pages.put("PRODUCTMAKER", "redirect:productmaker");
        pages.put("ADMIN", "redirect:admin");
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        if (login == null || login.equals("") || pass == null || pass.equals("")) {
            return "/login.jsp";
        }

        Optional<Worker> worker = userService.login(login);

        if(worker.isPresent() && pass.equals(worker.get().getPassword())){
            if (CommandUtility.checkUserIsLogged(request, worker.get().getLogin(), worker.get().getRole(), worker.get().getId())) {
                LOGGER.info("user with login: " + worker.get().getLogin() +" alredy logged");
                return "/WEB-INF/error.jsp";
            }
            LOGGER.info(worker.get().getRole().toString() + " with login: " + worker.get().getLogin() +" enter in system.");
            return pages.getOrDefault(worker.get().getRole().toString().toUpperCase(), pages.get("login"));
        }
        return "/login.jsp";
    }
}
