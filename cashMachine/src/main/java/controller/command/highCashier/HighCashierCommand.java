package controller.command.highCashier;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class HighCashierCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/highCashier/highCashierBase.jsp";
    }
}
