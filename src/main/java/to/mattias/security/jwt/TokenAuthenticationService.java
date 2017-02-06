package to.mattias.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import to.mattias.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * <h1>Created by Mattias on 2017-02-05.</h1>
 */
public class TokenAuthenticationService {
    private long EXPIRATIONTIME = 3600000; // 1 hour
    private final String secret = "thisIsASecret";
    private String tokenPrefix = "Bearer";
    private String headerString = "Authorization";

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

            if (username != null) {
                return new AuthenticatedUser(username);
            }
        }
        return null;
    }
}
