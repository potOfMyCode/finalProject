package controller.command.highCashier;

import controller.command.Command;
import model.service.highCashierService.HighCashierService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MakeZReport implements Command {
    private final static Logger LOGGER = Logger.getLogger(MakeZReport.class.getSimpleName());
    private HighCashierService highCashierService;

    public MakeZReport(HighCashierService highCashierService) {
        this.highCashierService = highCashierService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Map<Integer, Integer> zProducts = highCashierService.makeZReport();
        Map<String, Integer> nameProducts = new HashMap<>();

        zProducts.forEach((k, v)-> nameProducts.put(highCashierService.getProduct(k).getName(), v));

        Map<String, Integer> totalPriceForProduct= new HashMap<>();

        zProducts.forEach((k, v)->totalPriceForProduct
                .put(highCashierService.getProduct(k).getName(), highCashierService.getProduct(k).getPrice()*v));

        int totalSum= new ArrayList<Integer>(totalPriceForProduct.values()).stream().mapToInt(Integer::intValue).sum();

        request.getSession().setAttribute("nameProducts", nameProducts);
        request.getSession().setAttribute("totalPriceForProduct", totalPriceForProduct);
        request.getSession().setAttribute("totalSum", totalSum);

        LOGGER.info("Z-Report successfully created");

        return "/WEB-INF/highCashier/Z-Report.jsp";
    }
}
