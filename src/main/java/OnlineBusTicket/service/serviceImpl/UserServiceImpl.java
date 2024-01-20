package OnlineBusTicket.service.serviceImpl;

import OnlineBusTicket.dto.BookingDto;
import OnlineBusTicket.dto.UserDto;
import OnlineBusTicket.entity.BookingEntity;
import OnlineBusTicket.entity.User;
import OnlineBusTicket.exception.exc.InCorrectPasswordException;
import OnlineBusTicket.repository.BookingRepository;
import OnlineBusTicket.repository.UserRepository;
import OnlineBusTicket.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private BookingRepository bookingRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

            if (!Objects.equals(user.getPassword(), user.getConfirmPassword())) {
                throw new InCorrectPasswordException("User Not Created due to mismatch of password");
            }
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setConfirmPassword(passwordEncoder.encode(userDto.getConfirmPassword()));
       User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }
    @Override
    public UserDto updateUser(UserDto userDto,Long id) {
           User user= userRepository.findById(id).get();
           if (user!=null){
               if (userDto.getAddress() != null){
                   user.setAddress(userDto.getAddress());
               }
               if (userDto.getEmail()!=null){
                   user.setEmail(userDto.getEmail());
               }
               if (userDto.getFirstName() != null){
                   user.setFirstName(userDto.getFirstName());
               }
               if (userDto.getLastName() != null){
                   user.setLastName(userDto.getLastName());
               }
               if (userDto.getPhoneNumber() !=null){
                   user.setPhoneNumber(userDto.getPhoneNumber());
               }
               if (userDto.getPassword() !=null && userDto.getConfirmPassword() !=null){
                   if (userDto.getPassword().equals(userDto.getConfirmPassword())){
                       user.setPassword(passwordEncoder.encode(userDto.getPassword()));
                       user.setConfirmPassword(passwordEncoder.encode(userDto.getConfirmPassword()));
                   } else {
                       throw new InCorrectPasswordException("User Not Created due to mismatch of password");
                   }
               }


           }
               User updatedUser=   userRepository.save(user);
           return modelMapper.map(updatedUser,UserDto.class);
        }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public List<BookingDto> getAllBooking(Long userId) {
        return bookingRepository.findAllById(Collections.singleton(userId)).stream().map(Ajay->modelMapper.map(Ajay,BookingDto.class)).collect(Collectors.toList());
    }
}
