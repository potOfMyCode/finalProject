package controller.listeners;

import model.service.UserService;
import model.service.highCashierService.HighCashierService;
import model.service.productMakerService.ProductMakerService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final ServletContext servletContext = servletContextEvent.getServletContext();

        UserService userService = new UserService();
        servletContext.setAttribute("workers", userService.getAllWorkers());

        ProductMakerService productMakerService = new ProductMakerService();
        servletContext.setAttribute("products", productMakerService.getAllProducts());

        HighCashierService highCashierService = new HighCashierService();
        servletContext.setAttribute("checks", highCashierService.getAllChecks());

        final int course = 27;
        servletContext.setAttribute("course", course);

        String language="en";
        servletContext.setAttribute("language", language);

        String currency = "$";
        servletContext.setAttribute("currency", currency);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
