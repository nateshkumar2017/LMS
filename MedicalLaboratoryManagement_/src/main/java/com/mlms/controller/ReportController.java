package com.mlms.controller;

import com.mlms.dtos.ReportDTO;
import com.mlms.entities.Report;
import com.mlms.service.ReportGenerateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportGenerateServiceImpl reportService;

    @GetMapping("/generate")
    public ResponseEntity<ReportDTO> generateReport(@RequestParam Long orderId) {
        try {
            ReportDTO report = reportService.generateReport(orderId);
            if (report != null) {
                return ResponseEntity.ok(report);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}