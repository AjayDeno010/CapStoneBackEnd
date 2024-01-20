package OnlineBusTicket.service;

import OnlineBusTicket.dto.BookingDto;

import java.util.List;

public interface BookingService {
    BookingDto bookSeat(Long busId, List<Integer> seatNumbers, List<String> passengerNames);
    List<BookingDto> getBooking();

}
