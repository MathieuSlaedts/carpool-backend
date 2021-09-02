package be.carpool.carpool.security.jwt_support;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;

    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String createToken(String username, List<String> roles) {
        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.JWT_EXPIRATION_TIME))
                .withClaim("roles", new ArrayList<>(roles))
                .sign(Algorithm.HMAC512(SecurityConstants.JWT_ENCRYPTION_KEY));

        return SecurityConstants.JWT_PREFIX + token;
    }

    public String resolveToken(HttpServletRequest httpServletRequest) {
        String bearer = httpServletRequest.getHeader("Authorization");
        if(bearer != null && bearer.startsWith(SecurityConstants.JWT_PREFIX))
            return bearer.substring(SecurityConstants.JWT_PREFIX.length());
        return null;
    }

    public boolean validateToken(String token) {
        try {
            String username = JWT.require(Algorithm.HMAC512(SecurityConstants.JWT_ENCRYPTION_KEY))
                    .build()
                    .verify(token.replace(SecurityConstants.JWT_PREFIX, "")) // On re-vérifie que le token est nu
                    .getSubject();

            return username != null;
        }
        catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getUserName(String validatedNakedToken) {
        return JWT.decode(validatedNakedToken).getSubject();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "", userDetails.getAuthorities());
    }
}
