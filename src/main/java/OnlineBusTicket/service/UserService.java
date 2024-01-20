package OnlineBusTicket.service;

import OnlineBusTicket.dto.BookingDto;
import OnlineBusTicket.dto.UserDto;

import java.util.List;

public interface UserService {
   UserDto createUser(UserDto userDto);
   UserDto updateUser(UserDto userDto,Long id);
   void deleteUser(Long id);
    List<BookingDto> getAllBooking(Long userId);
}
