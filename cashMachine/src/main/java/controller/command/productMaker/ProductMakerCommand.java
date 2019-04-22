package controller.command.productMaker;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ProductMakerCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/productMaker/productMakerBase.jsp";
    }
}
