package controller.command.admin;

import controller.command.Command;
import model.entity.Worker;
import model.entity.enums.Role;
import model.entity.myException.NotUniqLoginException;
import model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class Registration implements Command {
    private final static Logger LOGGER = Logger.getLogger(Registration.class.getSimpleName());
    private UserService userService;

    public Registration(UserService userService) {
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String role = request.getParameter("role");
        String login = request.getParameter("login");
        String password = request.getParameter("password");


        if(name==null || role == null || login == null){
            return "/WEB-INF/admin/registration.jsp";
        }

        Worker worker = new Worker();
        worker.setName(name);
        worker.setRole(Role.valueOf(role));
        worker.setPassword(password);
        worker.setLogin(login);
        worker.setId(worker.hashCode());

        try {
            if (!userService.validateData(worker)) {
                return "/WEB-INF/admin/registration.jsp";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NotUniqLoginException ex){
            return "/WEB-INF/exceptionPages/notUniqLogin.jsp";
        }

        userService.addCashierToDB(worker);

        UserService userService = new UserService();
        request.getServletContext().setAttribute("workers", userService.getAllWorkers());

        LOGGER.info("worker: " + worker + " was successfully registered!");

        return "/WEB-INF/admin/registration.jsp";
    }
}
