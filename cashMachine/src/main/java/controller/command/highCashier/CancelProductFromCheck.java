package controller.command.highCashier;

import controller.command.Command;
import model.entity.Check;
import model.entity.myException.ProductNotExistException;
import model.service.highCashierService.HighCashierService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ConcurrentModificationException;

public class CancelProductFromCheck implements Command {
    private final static Logger LOGGER = Logger.getLogger(CancelProductFromCheck.class.getSimpleName());
    private HighCashierService highCashierService;

    public CancelProductFromCheck(HighCashierService highCashierService) {
        this.highCashierService = highCashierService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("idProduct");
        String attributeValue = request.getParameter("productAttribute");

        String idCheck = request.getParameter("currentCheck");

        if( attributeValue == null || id == null || attributeValue.equals("") || id.equals("")){
            return "/WEB-INF/highCashier/show_all_checks.jsp";
        }

        Check check = highCashierService.getCurrentCheck(Integer.valueOf(idCheck));

        try {
            highCashierService.cancelProductFromCheck(check, Integer.valueOf(id), Integer.valueOf(attributeValue));
        }catch (ProductNotExistException ex){
            return "/WEB-INF/exceptionPages/productNotExist.jsp";
        }catch (ConcurrentModificationException ex){
            ex.printStackTrace();
        }

        LOGGER.info("Product with id, attribute_value: " + id + ", " + attributeValue + " successfulle deleted from check");

        request.getServletContext().setAttribute("checks", highCashierService.getAllChecks());
        return "/WEB-INF/highCashier/highCashierBase.jsp";
    }
}
