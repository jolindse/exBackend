package to.mattias.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <h1>Created by Mattias on 2017-02-06.</h1>
 */
@Component
public class JWTAuthenticationFilter extends GenericFilterBean {

    private Logger logger = LoggerFactory.getLogger("kanban-logger");

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {
        try {
            Authentication authentication = tokenAuthenticationService.getAuthentication((HttpServletRequest)request);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request,response);
        } catch (IOException | ServletException | ExpiredJwtException e) {
            e.printStackTrace();
            logger.error(String.format("Error - %s", e.getMessage()));
        }
    }
}

