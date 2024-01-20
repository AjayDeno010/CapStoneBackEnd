package OnlineBusTicket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class BusRoutesEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long busId;
   private String travelAgencyName;
   @ElementCollection
   private List<String> fromLocations;
   @ElementCollection
   private List<String> toLocations;
   @ElementCollection
   private List<Date> dates;
   private Time departureTime;
   private Time arrivalTime;
   private Double rate;
   @ElementCollection
   @Column(length = 1000)
   private List<Integer> totalSeats;
   @ElementCollection
   @Column(length = 1000)
   private List<Integer> availableSeats;
   @OneToMany(mappedBy = "busRoute")
   private List<BookingEntity> bookings;

}
