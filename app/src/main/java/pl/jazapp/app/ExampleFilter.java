package pl.jazapp.app;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter("*")
public class ExampleFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (isUserLogged() || isSiteAllowed(req)) {
            chain.doFilter(req, res);
        } else {
            res.sendRedirect(req.getContextPath() + "/login");
        }
    }

    private boolean isSiteAllowed(HttpServletRequest req) {
        return "/login".equals(req.getServletPath());
    }

    private boolean isUserLogged() {
        return false;
    }
}
