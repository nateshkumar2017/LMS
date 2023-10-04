package com.mlms.repo;

import com.mlms.entities.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ReportStatusRepo extends JpaRepository<ReportStatus,String> {
    ReportStatus findById(int i);



    ReportStatus findByStatus(ReportStatus.StatusType statusType);
}
