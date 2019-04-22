package controller.command.highCashier;

import controller.command.Command;
import model.service.highCashierService.HighCashierService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CancelCheck implements Command {
    private final static Logger LOGGER = Logger.getLogger(CancelCheck.class.getSimpleName());
    private HighCashierService highCashierService;

    public CancelCheck(HighCashierService highCashierService) {
        this.highCashierService = highCashierService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("idCheck");
        highCashierService.cancelCheck(Integer.valueOf(id));

        LOGGER.info("Check with id: " + id + " successfully deleted!");

        request.getServletContext().setAttribute("checks", highCashierService.getAllChecks());
        return "/WEB-INF/highCashier/highCashierBase.jsp";
    }
}
