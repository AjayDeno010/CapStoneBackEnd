package OnlineBusTicket.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusRoutesDto {
    private Long busId;
    private String travelAgencyName;
    private List<String> fromLocations;
    private List<String> toLocations;
    private List<Date> dates;
    private Time departureTime;
    private Time arrivalTime;
    private Double rate;
    private List<Integer> totalSeats;
    private List<Integer> availableSeats;
}
