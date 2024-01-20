package OnlineBusTicket.service.serviceImpl;

import OnlineBusTicket.dto.BookingDto;
import OnlineBusTicket.entity.BookingEntity;
import OnlineBusTicket.entity.BusRoutesEntity;
import OnlineBusTicket.exception.exc.SeatNotAvailableException;
import OnlineBusTicket.repository.BookingRepository;
import OnlineBusTicket.repository.BusRouteRepository;
import OnlineBusTicket.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private BusRouteRepository busRouteRepository;

   private BookingRepository bookingRepository;
   private ModelMapper modelMapper;
    @Override
    public BookingDto bookSeat(Long busId, List<Integer> seatNumbers, List<String> passengerNames) {
        BusRoutesEntity busRoute = busRouteRepository.findById(busId)
                .orElseThrow(() -> new EntityNotFoundException("Bus route not found with id: " + busId));

        List<Integer> availableSeats = new ArrayList<>(busRoute.getAvailableSeats());

        for (Integer seatNumber : seatNumbers) {
            if (!availableSeats.contains(seatNumber)) {
                throw new SeatNotAvailableException("Seat " + seatNumber + " is not available for booking.");
            }

            // Remove the booked seat from available seats
            availableSeats.remove(seatNumber);
        }

        // Update the bus route with the modified available seats
        busRoute.setAvailableSeats(availableSeats);
        busRouteRepository.save(busRoute);

        // Create a new booking
        BookingEntity booking = new BookingEntity();
        booking.setSeatNumber(seatNumbers);
        booking.setPassengerName(passengerNames);
        booking.setBusRoute(busRoute);
        BookingEntity savedBooking = bookingRepository.save(booking);

        return modelMapper.map(savedBooking, BookingDto.class);
    }

    @Override
    public List<BookingDto> getBooking() {
        return bookingRepository.findAll().stream().map(map->modelMapper.map(map,BookingDto.class)).toList();
    }


}
