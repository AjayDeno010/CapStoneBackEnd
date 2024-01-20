package OnlineBusTicket.controller;

import OnlineBusTicket.dto.BusRoutesDto;
import OnlineBusTicket.dto.SeatsDto;
import OnlineBusTicket.service.BusService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController {
    @Autowired
    private BusService busService;
    @PostMapping("/create")
    public ResponseEntity<BusRoutesDto> createBus(@RequestBody BusRoutesDto busRoutesDto){
     BusRoutesDto savedBus=   busService.createBus(busRoutesDto);
     return new ResponseEntity<>(savedBus, HttpStatus.CREATED);
    }
    @GetMapping("/search")
    public ResponseEntity<List<BusRoutesDto>> searchBus(@RequestBody BusRoutesDto busRoutesDto){
        List<BusRoutesDto> buses= busService.searchBus(busRoutesDto.getFromLocations(),busRoutesDto.getToLocations(),busRoutesDto.getDates());
        return new ResponseEntity<>(buses,HttpStatus.OK);
    }
    @GetMapping("{busID}/checkSeat")
    public ResponseEntity<SeatsDto> checkSeat(@PathVariable Long busID){
      SeatsDto seatsDto= busService.checkSeat(busID);
     return new ResponseEntity<>(seatsDto,HttpStatus.OK);
    }
    @DeleteMapping("{busId}/{bookingId}/delete")
    public ResponseEntity<String> deleteBooking(@PathVariable Long busId,@PathVariable Long bookingId){
        busService.deleteBooking(busId,bookingId);
        return new ResponseEntity<>("deleted SuccessFully",HttpStatus.NO_CONTENT);
    }

}
