package hello;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ComputeRepository extends JpaRepository<Compute, Long> {

    List<Compute> findAllByEventDate(Date evtDate);

    List<Compute> findAllByEventDateBetween(Date startDate,Date endDate);

    List<Compute> findFirst10ByEventDateBefore(Date eventDate);

    List<Compute> findAllByIdBetween(Long beginId,Long endId);

    List<Compute> findTopByIdAfter(Long beginId);

    List<Compute> findTopByIdBefore(Long beginId);

}