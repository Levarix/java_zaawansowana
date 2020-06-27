package pl.jazapp.app.webapp;

import pl.jazapp.app.webapp.users.UserContext;

import javax.faces.application.ResourceHandler;
import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("*")
public class SecurityFilter extends HttpFilter {
    @Inject
    UserContext userContext;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if ((isUserLogged() || isSiteAllowed(req) || isResourceReq(req)) && !isSiteAllowedAdmin(req)) {
            chain.doFilter(req, res);
        }
        else if(isSiteAllowedAdmin(req) && isUserRoleAdmin()) {
            chain.doFilter(req, res);
        }
        else if(isUserLogged()){
            res.sendRedirect(req.getContextPath() + "/auctions/mine.xhtml");
        }
        else {
            res.sendRedirect(req.getContextPath() + "/login.xhtml");
        }

    }

    private boolean isSiteAllowedAdmin(HttpServletRequest req) {
        return "/departments/edit.xhtml".equals(req.getServletPath())
                || "/departments/list.xhtml".equals(req.getServletPath())
                || "/categories/edit.xhtml".equals(req.getServletPath())
                || "/categories/list.xhtml".equals(req.getServletPath());
    }

    private boolean isResourceReq(HttpServletRequest req) {
        return req.getRequestURI().startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");
    }
    private boolean isSiteAllowed(HttpServletRequest req) {
        return "/login.xhtml".equals(req.getServletPath())
            || "/register.xhtml".equals(req.getServletPath())
            || "/index.xhtml".equals(req.getServletPath());
    }

    private boolean isUserLogged() {
        return userContext.getIsLogged();
    }

    private boolean isUserRoleAdmin() {
        return userContext.isUserRoleAdmin();
    }

}