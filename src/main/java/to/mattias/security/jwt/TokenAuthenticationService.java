package to.mattias.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import to.mattias.beans.Login;
import to.mattias.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * <h1>Created by Mattias on 2017-02-06.</h1>
 */
@Service
public class TokenAuthenticationService {
    private long EXPIRATIONTIME = 3600000; // 1 hour
    private final String secret = "thisIsASecret";
    private String tokenPrefix = "Bearer";
    private String headerString = "Authorization";

//    private Logger logger = LoggerFactory.getLogger("kanban-logger");

    @Autowired
    private UserService userService;

    public void addAuthentication(HttpServletResponse response, String username) {
        // Generate a token
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        response.addHeader(headerString, tokenPrefix + " " + JWT);
        try {
            Login login = new Login(JWT, userService.findByUserName(username));
            ObjectMapper om = new ObjectMapper();
            response.getWriter().write(om.writeValueAsString(login));
        } catch (IOException e) {
//            logger.error(String.format("Login Error - %s", e.getMessage()));
        }
    }

    public Authentication getAuthentication(HttpServletRequest request) throws ExpiredJwtException {
        String token = request.getHeader(headerString);
            if (token != null) {
                // Parse the token
                String username = Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject();

                if (username != null) { // We managed to retrieve a user
                    return new AuthenticatedUser(userService.findByUserName(username));
                }
            }
        return null;
    }
}

