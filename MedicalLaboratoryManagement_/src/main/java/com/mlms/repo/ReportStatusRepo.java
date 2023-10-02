package com.mlms.repo;

import com.mlms.entities.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportStatusRepo extends JpaRepository<ReportStatus,String> {
    ReportStatus findById(int i);



    ReportStatus findByStatus(ReportStatus.StatusType statusType);
}
