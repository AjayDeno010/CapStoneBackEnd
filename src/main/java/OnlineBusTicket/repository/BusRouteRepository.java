package OnlineBusTicket.repository;

import OnlineBusTicket.dto.SeatsDto;
import OnlineBusTicket.entity.BusRoutesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository
public interface BusRouteRepository extends JpaRepository<BusRoutesEntity,Long> {
//        @Query("SELECT e FROM BusRoutesEntity e WHERE e.fromLocations = :from AND e.toLocations = :to AND e.dates = :date")
//        List<BusRoutesEntity> findByFromToAndDate(@Param("from") List<String> from, @Param("to") List<String> to, @Param("date") List<Date> date);
List<BusRoutesEntity> findByFromLocationsInAndToLocationsInAndDatesIn(List<String> fromLocations, List<String> toLocations, List<Date> dates);


}
