package OnlineBusTicket.service;

import OnlineBusTicket.dto.BusRoutesDto;
import OnlineBusTicket.dto.SeatsDto;
import OnlineBusTicket.entity.BusRoutesEntity;

import java.sql.Date;
import java.util.List;

public interface BusService {
  BusRoutesDto createBus(BusRoutesDto busRoutesDto);
  List<BusRoutesDto> searchBus(List<String> from, List<String> to, List<Date> date);
  SeatsDto checkSeat(Long busID);
  void deleteBooking(Long bookingID,Long BusId);
}
