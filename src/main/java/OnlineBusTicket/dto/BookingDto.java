package OnlineBusTicket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private Long bookingID;
    private List<Integer> seatNumber;
    private List<String> passengerName;
    private Long userId;
}
