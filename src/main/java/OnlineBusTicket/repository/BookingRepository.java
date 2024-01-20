package OnlineBusTicket.repository;

import OnlineBusTicket.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity,Long> {

}
