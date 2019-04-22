package controller.command.productMaker;

import controller.command.Command;
import model.entity.Product;
import model.service.productMakerService.ProductMakerService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CreatepProduct implements Command {
    private final static Logger LOGGER = Logger.getLogger(CreatepProduct.class.getSimpleName());
    private ProductMakerService productMakerService;

    public CreatepProduct(ProductMakerService productMakerService) {
        this.productMakerService = productMakerService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String attribute = request.getParameter("attribute_name");
        String price= request.getParameter("price");
        String attribute_value = request.getParameter("attribute_value");

        if(name==null || attribute == null || attribute_value == null){
            return "/WEB-INF/productMaker/addProduct.jsp";
        }

        Product product = new Product();
        product.setName(name);
        
        if(request.getServletContext().getAttribute("language").equals("en")) {
            int temp = (int) request.getServletContext().getAttribute("course");
            product.setPrice((int) (Double.valueOf(price)*temp));
        }else {
            product.setPrice(Integer.valueOf(price));
        }

        product.setAttribute(attribute);
        product.setAttribute_value(Integer.valueOf(attribute_value));
        product.setidProduct(product.hashCode());

        if (productMakerService.validateData(product)) {
            return "/WEB-INF/productMaker/addProduct.jsp";
        }

        productMakerService.createProduct(product);

        request.getServletContext().setAttribute("products", productMakerService.getAllProducts());

        LOGGER.info("Product: " + product + " successfully create!");

        return "/WEB-INF/productMaker/addProduct.jsp";
    }
}
