package OnlineBusTicket.service.jwt;

import OnlineBusTicket.entity.User;
import OnlineBusTicket.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      String userName=  authentication.getName();
     Object password= authentication.getCredentials();
        if(validateEmailAndPassword(userName, (String) password)){
            return new UsernamePasswordAuthenticationToken(userName,password);
        }
        else {
            throw new AuthenticationException("invalid Credential") {

            };
        }
    }
    public boolean validateEmailAndPassword(String email,String password){
        User user=  userRepository.findByEmail(email);
        boolean emailID= user.getEmail().equals(email);
        boolean encodedPassword= passwordEncoder.matches(password,user.getPassword());
        return emailID&encodedPassword;
    }
}
