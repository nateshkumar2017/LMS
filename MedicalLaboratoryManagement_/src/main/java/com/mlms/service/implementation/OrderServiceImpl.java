//package com.mlms.service;
//
//import com.mlms.dtos.OrderDTO;
//import com.mlms.entities.*;
//import com.mlms.repo.OrderRepo;
//import com.mlms.repo.PatientRepo;
//import com.mlms.repo.ReportStatusRepo;
//import com.mlms.repo.TestRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class OrderServiceImpl {
//
//    @Autowired
//    private PatientRepo patientRepository;
//
//    @Autowired
//    private TestRepo testRepository;
//
//    @Autowired
//    private OrderRepo orderRepository;
//
//    @Autowired
//    private ReportStatusRepo reportStatusRepository;
//
//    public Order createOrderAndInvoiceForPatient(OrderDTO request) {
//        Patient patient = patientRepository.findByContactNo(request.getContactNo());
//
//        if (patient != null) {
//            // Create a list to store orders for multiple tests
//            List<Order> orders = new ArrayList<>();
//            float totalAmount = 0.0f; // Initialize the total amount
//
//            for (String testCode : request.getTestCodes()) {
//                // Retrieve the test by test code
//                Test test = testRepository.findByTestCode(testCode);
//
//                if (test != null) {
//                    // Create an order for the patient
//                    Order order = new Order();
//                    order.setPatient(patient);
//                    order.setTest(test);
//                    ReportStatus pendingStatus = reportStatusRepository.findByStatus(ReportStatus.StatusType.PENDING);
//
//                    order.setReportStatus(pendingStatus);
//                    order.setOrderCreatedDateTime(new Date()); // Set the order date
//
//                    // Calculate the total amount for the current test and add it to the overall total
//                    float testTotalAmount = calculateTotalAmount(test, request.getDiscount());
//                    totalAmount += testTotalAmount;
//
//                    // Create an invoice for the order
//                    Invoice invoice = new Invoice();
//                    invoice.setPatient(patient);
//                    invoice.setOrder(order);
//                    invoice.setTotalAmount(testTotalAmount); // Set the total amount for this test
//                    invoice.setDiscount(request.getDiscount());
//
//                    // Link the order to the invoice
//                    order.setInvoice(invoice);
//
//                    // Add the order to the list
//                    orders.add(order);
//                } else {
//                    throw new RuntimeException("Test not found with test code: " + testCode);
//                }
//            }
//
//            // Save all orders and invoices to the database
//            List<Order> createdOrders = orderRepository.saveAll(orders);
//
//            return createdOrders.get(0); // Return the first order as an example
//        } else {
//            throw new RuntimeException("Patient not found with contact number: " + request.getContactNo());
//        }
//    }
//
//    private float calculateTotalAmount(Test test, float discount) {
//        float basePrice = test.getPrice();
//        float totalAmount = basePrice;
//
//        // Apply the discount if applicable
//        if (discount > 0) {
//            totalAmount -= totalAmount * (discount / 100.0f);
//        }
//
//        return totalAmount;
//    }
//}


package com.mlms.service.implementation;

import com.mlms.dtos.OrderDTO;
import com.mlms.dtos.OrderResponseDTO; // Import the new DTO
import com.mlms.dtos.OrderStatusDTO;
import com.mlms.entities.*;
import com.mlms.repo.OrderRepo;
import com.mlms.repo.PatientRepo;
import com.mlms.repo.ReportStatusRepo;
import com.mlms.repo.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl {

    @Autowired
    private PatientRepo patientRepository;

    @Autowired
    private TestRepo testRepository;

    @Autowired
    private OrderRepo orderRepository;

    @Autowired
    private ReportStatusRepo reportStatusRepository;

    public OrderResponseDTO createOrderAndInvoiceForPatient(OrderDTO request) {
        Patient patient = patientRepository.findByContactNo(request.getContactNo());

        if (patient != null) {

            List<Order> orders = new ArrayList<>();
            float totalAmount = 0.0f;

            for (String testCode : request.getTestCodes()) {
                Test test = testRepository.findByTestCode(testCode);

                if (test != null) {

                    Order order = new Order();
                    order.setPatient(patient);
                    order.setTest(test);
                    ReportStatus pendingStatus = reportStatusRepository.findByStatus(ReportStatus.StatusType.PENDING);

                    order.setReportStatus(pendingStatus);
                    order.setOrderCreatedDateTime(new Date());

                    float testTotalAmount = calculateTotalAmount(test, request.getDiscount());
                    totalAmount += testTotalAmount;

                    Invoice invoice = new Invoice();
                    invoice.setPatient(patient);
                    invoice.setOrder(order);
                    invoice.setTotalAmount(testTotalAmount);
                    invoice.setDiscount(request.getDiscount());

                    order.setInvoice(invoice);

                    orders.add(order);
                } else {
                    throw new RuntimeException("Test not found with test code: " + testCode);
                }
            }

            List<Order> createdOrders = orderRepository.saveAll(orders);

            OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
            orderResponseDTO.setPatientId(patient.getId());
            orderResponseDTO.setPatientName(patient.getPatientName());
            orderResponseDTO.setPatientContactNo(patient.getContactNo());
            orderResponseDTO.setPatientGender(patient.getGender());
            orderResponseDTO.setPatientAge(patient.getAge());
            orderResponseDTO.setPatientReferedby(patient.getReferedBy());
            orderResponseDTO.setOrderTime(new Date());

            List<String> testNamesWithAmounts = new ArrayList<>();

            for (Order createdOrder : createdOrders) {
                Test test = createdOrder.getTest();
                float testTotalAmount = calculateTotalAmount(test, request.getDiscount());

                testNamesWithAmounts.add(test.getTestName() + " - " + testTotalAmount);
            }

            orderResponseDTO.setTestNames(testNamesWithAmounts);
            orderResponseDTO.setTotalAmount(totalAmount);
            orderResponseDTO.setDiscount(request.getDiscount());

            float amountAfterDiscount = totalAmount - (totalAmount * (request.getDiscount() / 100.0f));
            orderResponseDTO.setAmountAfterDiscount(amountAfterDiscount);

            orderResponseDTO.setInvoiceId(createdOrders.get(0).getInvoice().getId());

            return orderResponseDTO;
        } else {
            throw new RuntimeException("Patient not found with contact number: " + request.getContactNo());
        }
    }

    private float calculateTotalAmount(Test test, float discount) {
        float basePrice = test.getPrice();
        float totalAmount = basePrice;

        if (discount > 0) {
            totalAmount -= totalAmount * (discount / 100.0f);
        }

        return totalAmount;
    }


    public List<OrderStatusDTO> getPendingOrders() {
        ReportStatus pendingStatus = reportStatusRepository.findByStatus(ReportStatus.StatusType.PENDING);
        List<Order> pendingOrders = orderRepository.findByReportStatus(pendingStatus);

        List<OrderStatusDTO> orderStatusDTOs = pendingOrders.stream()
                .map(order -> {
                    OrderStatusDTO dto = new OrderStatusDTO();
                    dto.setOrderId(order.getId());
                    dto.setTestCode(order.getTest().getTestCode());
                    dto.setPatientId(order.getPatient().getId());
                    // Set other fields based on your requirements
                    return dto;
                })
                .collect(Collectors.toList());

        return orderStatusDTOs;
    }
}
