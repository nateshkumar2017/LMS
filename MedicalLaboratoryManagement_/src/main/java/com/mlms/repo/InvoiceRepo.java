package com.mlms.repo;

import com.mlms.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepo extends JpaRepository<Invoice,String> {
}
