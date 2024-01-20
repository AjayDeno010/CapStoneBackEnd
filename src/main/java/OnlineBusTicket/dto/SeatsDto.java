package OnlineBusTicket.dto;

import OnlineBusTicket.entity.BusRoutesEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatsDto {
    private Long busId;
    @ElementCollection
    @Column(length = 1000)
    private List<Integer> totalSeats;
    @ElementCollection
    @Column(length = 1000)
    private List<Integer> availableSeats;
    public SeatsDto(BusRoutesEntity busRoutesEntity) {
        this.busId = busRoutesEntity.getBusId();
        this.totalSeats = busRoutesEntity.getTotalSeats();
        this.availableSeats = busRoutesEntity.getAvailableSeats();
    }

}
