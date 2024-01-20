package OnlineBusTicket.controller;

import OnlineBusTicket.dto.BookingDto;
import OnlineBusTicket.dto.BusRoutesDto;
import OnlineBusTicket.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@AllArgsConstructor
public class BookingController {
    private BookingService bookingService;
    @PostMapping("{busId}/bookSeat")
    public ResponseEntity<BookingDto> bookSeat(@PathVariable Long busId, @RequestBody BookingDto bookingDto){
      BookingDto bookedTicket=  bookingService.bookSeat(busId,bookingDto.getSeatNumber(),bookingDto.getPassengerName());
      return new ResponseEntity<>(bookedTicket, HttpStatus.CREATED);
    }
    @GetMapping("/getAllBooking")
    public ResponseEntity<List<BookingDto>> getAllBooking(){
      List<BookingDto> allBookings = bookingService.getBooking();
      return new ResponseEntity<>(allBookings,HttpStatus.OK);
    }

}
