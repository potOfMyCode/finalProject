package controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

public class LangFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final int ukrCourse = 1;
        final int engCourse = 27;
        final String currencyUSD = "$";
        final String currencyGrn= "₴";
        String lang = servletRequest.getParameter("lang");
        if (lang != null) {

            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            if (lang.equals("ua")) {
                Config.set(request.getSession(), Config.FMT_LOCALE, new Locale("uk", "UA"));
                request.getServletContext().setAttribute("language", lang);
                request.getServletContext().setAttribute("course", ukrCourse);
                request.getServletContext().setAttribute("currency", currencyGrn);
            }
            if (lang.equals("en")) {
                Config.set(request.getSession(), Config.FMT_LOCALE, new Locale("en", "US"));
                request.getServletContext().setAttribute("language", lang);
                request.getServletContext().setAttribute("course", engCourse);
                request.getServletContext().setAttribute("currency", currencyUSD);
            }
            response.sendRedirect(request.getHeader("referer"));
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
