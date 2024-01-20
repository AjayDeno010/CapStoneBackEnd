package OnlineBusTicket.service.jwt;
import OnlineBusTicket.entity.User;
import OnlineBusTicket.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Service
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
   private JwtUtils jwtUtils;
    private UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //header->token is valid-> role based authtentication->securitycontext
        System.out.println("Request URI: " + request.getRequestURI());
        String authorizationToken=  request.getHeader("Authorization");
        if(authorizationToken==null|| authorizationToken.isEmpty()||!authorizationToken.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        String token= authorizationToken.split(" ")[1];
        if(!jwtUtils.validate(token)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid JWT Token");
            filterChain.doFilter(request,response);
            return;
        }
        String userName= jwtUtils.getUserName(token);
//        UserEntity user= userRepository.findByEmail(userName)
        User user= userRepository.findByEmail(userName);
        UsernamePasswordAuthenticationToken token1= new
//                UsernamePasswordAuthenticationToken(userName,user.getPassword(), Collections.emptyList());
        UsernamePasswordAuthenticationToken(userName,user.getPassword(), Collections.emptyList());
        token1.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        //  SecurityContext
        try {
            SecurityContextHolder.getContext().setAuthentication(token1);
            filterChain.doFilter(request,response);
        }finally {
            SecurityContextHolder.clearContext();
        }
    }
}