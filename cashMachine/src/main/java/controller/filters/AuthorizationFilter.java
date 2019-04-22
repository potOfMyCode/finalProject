package controller.filters;

import controller.command.*;
import controller.command.admin.AdminCommand;
import controller.command.admin.Registration;
import controller.command.admin.ShowAllWorker;
import controller.command.cashier.*;
import controller.command.cashier.ShowAllChecksForCashier;
import controller.command.highCashier.*;
import controller.command.productMaker.CreatepProduct;
import controller.command.productMaker.ProductMakerCommand;
import controller.command.productMaker.ShowAllProduct;
import controller.command.productMaker.UpdateProduct;
import model.entity.enums.Role;
import model.service.UserService;
import model.service.cashierService.CashierService;
import model.service.highCashierService.HighCashierService;
import model.service.productMakerService.ProductMakerService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationFilter implements Filter {
    private Map<String, Command> commands = new HashMap<>();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;

        String role;
        if(request.getSession().getAttribute("role") == null){
            role = Role.UNKNOWN.toString();
        }else{
            role = request.getSession().getAttribute("role").toString();
        }

        commands.clear();

        if (role.equals(Role.UNKNOWN.toString())) {
            commands.put("login", new Login(new UserService()));
            commands.put("home", new Home());
            commands.put("cashier", new CashierCommand());
            commands.put("highcashier", new HighCashierCommand());
            commands.put("productmaker",new ProductMakerCommand());
            commands.put("admin", new AdminCommand());
        }

        if (role.equals(Role.ADMIN.toString())) {
            commands.put("logout", new LogOut());
            commands.put("admin/show_all", new ShowAllWorker());
            commands.put("admin/registration", new Registration(new UserService()));
            commands.put("home", new Home());
            commands.put("admin", new AdminCommand());
        }
        if (role.equals(Role.CASHIER.toString())) {
            commands.put("logout", new LogOut());
            commands.put("home", new Home());
            commands.put("cashier",new CashierCommand());
            commands.put("cashier/createCheck",new CreateCheck());
            commands.put("cashier/addProductToCheck", new AddProductToCheck(new CashierService()));
            commands.put("cashier/closeCheck", new CloseCheck(new CashierService()));
            commands.put("cashier/show_all_checks", new ShowAllChecksForCashier());
        }
        if (role.equals(Role.HIGHCASHIER.toString())) {
            commands.put("logout", new LogOut());
            commands.put("home", new Home());
            commands.put("highcashier",new HighCashierCommand());
            commands.put("highcashier/cancelProductFromCheck", new CancelProductFromCheck(new HighCashierService()));
            commands.put("highcashier/cancelCheck", new CancelCheck(new HighCashierService()));
            commands.put("highcashier/X-Report", new MakeXReport(new HighCashierService()));
            commands.put("highcashier/Z-Report", new MakeZReport(new HighCashierService()));
            commands.put("highcashier/show_all_checks", new ShowAllChecks(new HighCashierService()));
        }
        if (role.equals(Role.PRODUCTMAKER.toString())) {
            commands.put("logout", new LogOut());
            commands.put("home", new Home());
            commands.put("productmaker",new ProductMakerCommand());
            commands.put("productmaker/addProduct", new CreatepProduct(new ProductMakerService()));
            commands.put("productmaker/show_all", new ShowAllProduct());
            commands.put("productmaker/updateProduct", new UpdateProduct(new ProductMakerService()));
        }


        request.getSession().setAttribute("commands", commands);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
