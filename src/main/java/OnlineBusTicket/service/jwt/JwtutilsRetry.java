//package OnlineBusTicket.service.jwt;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//
//import javax.crypto.SecretKey;
//import java.security.Key;
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//public class JwtutilsRetry {
//    private static final  int ExpiredInMs= 600*100;
//    private final static Key key= Keys.secretKeyFor(SignatureAlgorithm.HS256);
//
//    public Map<String,String> generate(String userName){
//          Date  ExpireTime= new Date(System.currentTimeMillis()+ExpiredInMs);
//
//         String  token = Jwts.builder()
//                .setSubject(userName)
//                .setIssuer("Ajay.com")
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(ExpireTime)
//                .signWith(key)
//                .compact();
//
//         Map<String,String> response= new HashMap<>();
//         response.put("token",token);
//         response.put("ExpireInMinus",String.valueOf(ExpireTime));
//         return response;
//    }
//
//    public String getName(String token){
//        Claims claims= getClaims(token);
//       return claims.getSubject();
//    }
//    public boolean isExpired(String token){
//        Claims claims=getClaims(token);
//       return claims.getExpiration().after(new Date(System.currentTimeMillis()));
//    }
//
//    public Claims getClaims(String token){
//        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
//    }
//}
