package controller.command.highCashier;

import controller.command.Command;
import model.entity.Check;
import model.service.highCashierService.HighCashierService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAllChecks implements Command {
    private HighCashierService highCashierService;

    public ShowAllChecks(HighCashierService highCashierService) {
        this.highCashierService = highCashierService;
    }

    @Override
    public String execute(HttpServletRequest request) {int page = 1;
        int recordsPerPage =1;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        List<Check> checks = highCashierService.getAllChecks();
        int noOfRecords = checks.size();
        checks = generateNeedsRecords(checks, (page-1)*recordsPerPage, (page-1)*recordsPerPage+1);

        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("checkList", checks);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        return "/WEB-INF/highCashier/show_all_checks.jsp";
    }
    private List<Check> generateNeedsRecords(List<Check> list, int from, int to){
        return list.subList(from, to);
    }
}