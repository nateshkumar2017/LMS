package com.mlms.repo;

import com.mlms.entities.Order;
import com.mlms.entities.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface OrderRepo extends JpaRepository<Order,String> {
    //List<Order> findByStatusId(int statusId);
    List<Order> findByReportStatus(ReportStatus reportStatus);

  //  Optional<Object> findById(Long orderId);
    Optional<Order> findById(Long testId);

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.reportStatus = ?2 WHERE o.id = ?1")
    void updateOrderStatus(Long orderId, ReportStatus status);


    //Optional<Order> findById(Long id);
}
