package OnlineBusTicket.service.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    private static final int expireInMs= 600*1000;
    private final static Key key= Keys.secretKeyFor(SignatureAlgorithm.HS256);
public Map<String, String> generate(String userName) {
    Date expirationDate = new Date(System.currentTimeMillis() + expireInMs);

    String token = Jwts.builder()
            .setSubject(userName)
            .setIssuer("Ajay.com")
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(expirationDate)
            .signWith(key)
            .compact();

    Map<String, String> response = new HashMap<>();
    response.put("token", token);
    response.put("expiresIn", String.valueOf(expireInMs));

    return response;
}

    public boolean validate(String token){
        if(getUserName(token)!=null && isExpired(token)){
            return true;

        } else {
            return false;
        }
    }
    public String getUserName(String token){
        Claims claims= getClaims(token);
        return claims.getSubject();

    }
    public boolean isExpired(String token){
        Claims claims=   getClaims(token);
        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    private Claims getClaims(String token){
        return  Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
}
