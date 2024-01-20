package OnlineBusTicket.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Email
    @Column(nullable = false)
    private String email;

    private String address;
    private String password;
    private String confirmPassword;

    @Column(nullable = false)
    private Long phoneNumber;

    // Getters and setters
}
