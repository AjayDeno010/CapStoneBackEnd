package OnlineBusTicket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingID;
    private List<Integer> seatNumber; // this will be added in booked seat this to be removed in available seat
    private List<String> passengerName;
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "bus_route_id") // Adjust the column name as per your actual database schema
    private BusRoutesEntity busRoute;
}
