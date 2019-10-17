package com.obinna.libraryuser.security;

import com.obinna.libraryuser.model.Role;
import com.obinna.libraryuser.utils.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${secret-key}")// done
    private String secretKey;

    @Value("${expire-length}")// done
    private long validityInMilliseconds = 1800000; // 30 mins

    @Autowired
    private MyUserDetails myUserDetails;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, List<Role> roles) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails details = myUserDetails.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(details, "", details.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            logger.info("*************************** Token not null ************************");
            return bearerToken.substring(7);
        }
        logger.info("*************************** Token is null ************************");
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            Date dateIssued = claims.getIssuedAt();
            Date expirationDate = claims.getExpiration();
            String audience = claims.getAudience();
            String issuer = claims.getIssuer();
            String id = claims.getId();
            logger.info("******** Date issued: {} ********** " +
                    "\n ******** Expiration date: {} **********" +
                    "\n ******** Audience: {} **********" +
                    "\n ******** Issuer: {} **********" +
                    "\n ******** Id: {} **********", dateIssued, expirationDate, audience, issuer, id);
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token.trim());
            logger.info("*************************** Token successfully validated ************************");
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            logger.info("*************************** Token not validated ************************");
            throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
