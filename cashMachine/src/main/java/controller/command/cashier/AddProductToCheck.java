package controller.command.cashier;

import controller.command.Command;
import model.entity.Check;
import model.entity.Product;
import model.entity.myException.ProductNotExistException;
import model.entity.myException.ProductOutOfStockException;
import model.service.cashierService.CashierService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class AddProductToCheck implements Command {
    private final static Logger LOGGER = Logger.getLogger(AddProductToCheck.class.getSimpleName());
    private CashierService cashierService;

    public AddProductToCheck(CashierService cashierService) {
        this.cashierService = cashierService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String idProduct = request.getParameter("idProduct");
        String attribute_value = request.getParameter("attribute_value");

        if(idProduct==null || attribute_value == null){
            return "/WEB-INF/cashier/createCheck.jsp";
        }
        Product product;
        try {
            product = cashierService.getProduct(Integer.parseInt(idProduct), Integer.valueOf(attribute_value));
        }catch (ProductNotExistException ex){
            return "/WEB-INF/exceptionPages/productNotExist.jsp";
        }catch (ProductOutOfStockException ex){
            return "/WEB-INF/exceptionPages/productOutOfStock.jsp";
        }

        Check check = (Check) request.getSession().getAttribute("check");

        Map<Integer, Integer> products = check.getProducts();
        if(products == null)
            products = new HashMap<>();
        Integer temp = products.putIfAbsent(Integer.valueOf(idProduct), Integer.valueOf(attribute_value));

        if(temp != null){
            int cost = check.getPrice();
            cost -= product.getPrice()*temp;
            check.setPrice(cost);
            attribute_value = String.valueOf(Integer.valueOf(attribute_value)+temp);
            products.put(Integer.valueOf(idProduct), Integer.valueOf(attribute_value));
        }

        check.setProducts(products);

       /* int result = 0;

        for (Map.Entry<Integer, Integer> entry : products.entrySet()) {
            result += entry.getValue()*cashierService.getProduct(entry.getKey()).getPrice();
        }

        System.out.println("Result: " + result);

        int cost = check.getPrice();
        System.out.println("Cost: " + cost);

        if (cost != result)
            cost += product.getPrice()*Integer.valueOf(attribute_value);

        check.setPrice(cost);

        System.out.println("Check is: " + check);*/

        int cost = check.getPrice();
        cost += product.getPrice()*Integer.valueOf(attribute_value);
        check.setPrice(cost);

        request.getSession().setAttribute("check", check);

        LOGGER.info("Product with id, attribute_value: " + idProduct + ", " + attribute_value + " successfully added to chec: " + check);

        return "/WEB-INF/cashier/createCheck.jsp";
    }
}
