package controller.command.cashier;

import controller.command.Command;
import model.entity.Check;
import model.entity.myException.ProductNotExistException;
import model.service.cashierService.CashierService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class CloseCheck implements Command {
    private final static Logger LOGGER = Logger.getLogger(CloseCheck.class.getSimpleName());
    private CashierService cashierService;

    public CloseCheck(CashierService cashierService) {
        this.cashierService = cashierService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Check check = (Check) request.getSession().getAttribute("check");
        check.setidCheck(check.hashCode());

        cashierService.createCheck(check);
        Map<Integer, Integer> products = check.getProducts();

        try {
            products.forEach((k, v) -> cashierService.addProductInCheck(check, cashierService.getProduct(k, 0)));
        }catch (ProductNotExistException ex){
            return "/WEB-INF/exceptionPages/productNotExist.jsp";
        }

        LOGGER.info("check: " + check + " was successfully created");
        request.getSession().setAttribute("check", null);
        return "/WEB-INF/cashier/cashierBase.jsp";
    }
}
