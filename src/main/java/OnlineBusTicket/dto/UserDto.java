package OnlineBusTicket.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    @Email
    private  String email;
    private String address;
    private  String password;
    private String confirmPassword;
    private Long phoneNumber;
    private String message;


}
