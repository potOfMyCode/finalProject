package controller.command.cashier;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ShowAllChecksForCashier implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/cashier/show_all_checks.jsp";
    }
}
