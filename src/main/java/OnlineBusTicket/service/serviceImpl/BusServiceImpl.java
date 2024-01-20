package OnlineBusTicket.service.serviceImpl;

import OnlineBusTicket.dto.BusRoutesDto;
import OnlineBusTicket.dto.SeatsDto;
import OnlineBusTicket.entity.BookingEntity;
import OnlineBusTicket.entity.BusRoutesEntity;
import OnlineBusTicket.repository.BookingRepository;
import OnlineBusTicket.repository.BusRouteRepository;
import OnlineBusTicket.service.BusService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BusServiceImpl implements BusService {
    private BusRouteRepository busRouteRepository;
    private BookingRepository bookingRepository;
    private ModelMapper modelMapper;
    @Override
    public BusRoutesDto createBus(BusRoutesDto busRoutesDto) {
       BusRoutesEntity bus= modelMapper.map(busRoutesDto, BusRoutesEntity.class);
        BusRoutesEntity savedBus=  busRouteRepository.save(bus);
        return modelMapper.map(savedBus, BusRoutesDto.class);
    }

    @Override
    public List<BusRoutesDto> searchBus(List<String> from, List<String> to, List<Date> date) {
       List<BusRoutesDto> filter= busRouteRepository.findByFromLocationsInAndToLocationsInAndDatesIn(from,to,date).stream().map(map->modelMapper.map(map,BusRoutesDto.class)).toList();

        return filter;
    }

    @Override
    public SeatsDto checkSeat(Long busID) {
      BusRoutesEntity busRoutesEntity=  busRouteRepository.findById(busID).get();
        return new SeatsDto(busRoutesEntity) ;
    }

@Override
public void deleteBooking(Long busId, Long bookingId) {
    BusRoutesEntity bus = busRouteRepository.findById(busId).orElseThrow(() -> new EntityNotFoundException("Bus route not found with id: " + busId));
    BookingEntity booking = bookingRepository.findById(bookingId).orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + bookingId));

    List<Integer> seatNumbers = booking.getSeatNumber();
    List<Integer> availableSeatNumbers = bus.getAvailableSeats();

    availableSeatNumbers.addAll(seatNumbers);

    busRouteRepository.save(bus);
     bookingRepository.delete(booking);
}
}
