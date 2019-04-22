package controller.command.cashier;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class CashierCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/cashier/cashierBase.jsp";
    }
}
