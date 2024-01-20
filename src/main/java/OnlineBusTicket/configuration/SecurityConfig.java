package OnlineBusTicket.configuration;

import OnlineBusTicket.service.jwt.JwtTokenFilter;
import OnlineBusTicket.service.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private JwtTokenFilter jwtTokenFilter;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeHttpRequests(authroize-> {
                    authroize.requestMatchers(HttpMethod.POST, "api/create").permitAll();
                    authroize.requestMatchers(HttpMethod.POST, "api/login").permitAll();
                    authroize.anyRequest().authenticated();}
                ).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
     return httpSecurity.build();

    }
}
