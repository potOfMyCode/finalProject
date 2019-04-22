package controller.command.productMaker;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ShowAllProduct implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/productMaker/show_all.jsp";
    }
}
