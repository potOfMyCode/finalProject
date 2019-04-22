package controller.command.highCashier;

import controller.command.Command;
import model.service.highCashierService.HighCashierService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MakeXReport implements Command {
    private final static Logger LOGGER = Logger.getLogger(MakeXReport.class.getSimpleName());
    private HighCashierService highCashierService;

    public MakeXReport(HighCashierService highCashierService) {
        this.highCashierService = highCashierService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Map<String, Integer> zWorkers = highCashierService.makeXreport();

        request.getSession().setAttribute("zWorkers", zWorkers);

        LOGGER.info("X-Report successfully created");

        return "/WEB-INF/highCashier/X-Report.jsp";
    }
}
