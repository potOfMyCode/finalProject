package controller.command.admin;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ShowAllWorker implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/admin/show_all.jsp";
    }
}
