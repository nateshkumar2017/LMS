package com.mlms.repo;

import com.mlms.entities.Order;
import com.mlms.entities.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order,String> {
    //List<Order> findByStatusId(int statusId);
    List<Order> findByReportStatus(ReportStatus reportStatus);

  //  Optional<Object> findById(Long orderId);
    Optional<Order> findById(Long testId);

    //Optional<Order> findById(Long id);
}
