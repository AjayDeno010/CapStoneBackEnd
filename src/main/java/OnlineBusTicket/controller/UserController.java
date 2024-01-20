package OnlineBusTicket.controller;

import OnlineBusTicket.dto.BookingDto;
import OnlineBusTicket.dto.LoginRequestDto;
import OnlineBusTicket.dto.UserDto;
import OnlineBusTicket.service.UserService;
import OnlineBusTicket.service.jwt.CustomAuthenticationManager;
import OnlineBusTicket.service.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private CustomAuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Map<String,String>> accessToken(@RequestBody LoginRequestDto loginRequestDto){
        UsernamePasswordAuthenticationToken token= new
                UsernamePasswordAuthenticationToken(
                loginRequestDto.getUserName(),loginRequestDto.getPassword());
        // to validate user name and password use authtentication manager
        authenticationManager.authenticate(token);
        //genrate JWT token
        Map<String, String> jwtMap = jwtUtils.generate(loginRequestDto.getUserName());
        return new ResponseEntity<>(jwtMap,HttpStatus.OK);
    }
    @PostMapping("create")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PutMapping("{id}/update")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,@RequestBody UserDto userDto){
       UserDto updatedUser= userService.updateUser(userDto,id);
       return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }
    @DeleteMapping("{id}/delete")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("successfully deleted",HttpStatus.NO_CONTENT);
    }
    @GetMapping("{userId}/getAllBooking")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<BookingDto>> getAllBooking(@PathVariable Long userId){
      List<BookingDto> allBooking=  userService.getAllBooking(userId);
      return new ResponseEntity<>(allBooking,HttpStatus.OK);
    }

}
