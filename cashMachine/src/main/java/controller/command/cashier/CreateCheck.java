package controller.command.cashier;

import controller.command.Command;
import model.entity.Check;

import javax.servlet.http.HttpServletRequest;

public class CreateCheck implements Command {

    @Override
    public String execute(HttpServletRequest request) {


        Check check = new Check();

        check.setidWorker((int)request.getSession().getAttribute("userId"));
        check.setidCheck(-1);
        check.setPrice(0);

        request.getSession().setAttribute("check", check);

        return "/WEB-INF/cashier/createCheck.jsp";
    }
}
