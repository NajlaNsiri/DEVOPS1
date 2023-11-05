package tn.esprit.devops_project.services;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.Iservices.IInvoiceService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class InvoiceServiceImplTest {

    @Autowired
    IInvoiceService iInvoiceService;
    @Mock
    private OperatorRepository operatorRepository;
    @Mock
    private InvoiceRepository invoiceRepository;

    @Test
    @Order(1)
    void retrieveAllInvoices() {
        // Create a list of sample invoices
        List<Invoice> sampleInvoices = new ArrayList<>();
        for (long i = 1; i <= 10; i++) {
            Invoice invoice = new Invoice();
            invoice.setIdInvoice(i);
            // Add other properties as needed
            sampleInvoices.add(invoice);
        }

        // Mock the behavior of the InvoiceRepository
        when(invoiceRepository.findAll()).thenReturn(sampleInvoices);

        // Execute the method to retrieve all invoices
        List<Invoice> invoices = iInvoiceService.retrieveAllInvoices();

        // Assertions
        assertNotNull(invoices);
        assertEquals(10, invoices.size());
    }

    @Test
    @Order(3)
    void cancelInvoice() {
        // Create a test invoice with a known ID (adjust the ID as needed)
        Long invoiceId = 1L;

        // Create a sample invoice
        Invoice sampleInvoice = new Invoice();
        sampleInvoice.setIdInvoice(invoiceId);
        // Set other properties as needed

        // Mock the behavior of the InvoiceRepository
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(sampleInvoice));

        // Execute the cancelInvoice method
        iInvoiceService.cancelInvoice(invoiceId);

        // Assertions
        verify(invoiceRepository, times(1)).save(sampleInvoice);
        assertTrue(sampleInvoice.getArchived());
    }

    @Test
    @Order(2)
    void retrieveInvoice() {
        Long invoiceIdToRetrieve = 1L; // 1L represents the existing ID

        // Create a sample invoice
        Invoice sampleInvoice = new Invoice();
        sampleInvoice.setIdInvoice(invoiceIdToRetrieve);
        // Set other properties as needed

        // Mock the behavior of the InvoiceRepository
        when(invoiceRepository.findById(invoiceIdToRetrieve)).thenReturn(Optional.of(sampleInvoice));

        // Execute the method to retrieve the specific invoice
        Invoice retrievedInvoice = iInvoiceService.retrieveInvoice(invoiceIdToRetrieve);

        // Assertions
        assertNotNull(retrievedInvoice);
        assertEquals(invoiceIdToRetrieve, retrievedInvoice.getIdInvoice());
        // Add other assertions for other properties as needed
    }

    @Test
    @Order(4)
    void getInvoicesBySupplier() {
        Long supplierId = 1L; // Adjust to the ID of an existing supplier

        // Create a list of sample invoices associated with the supplier
        List<Invoice> sampleInvoices = new ArrayList<>();
        for (long i = 1; i <= 2; i++) {
            Invoice invoice = new Invoice();
            invoice.setIdInvoice(i);
            // Set the supplier property to match the test supplier
            Supplier testSupplier = new Supplier();
            testSupplier.setIdSupplier(supplierId);
            invoice.setSupplier(testSupplier);
            // Set other properties as needed
            sampleInvoices.add(invoice);
        }

        // Mock the behavior of the InvoiceRepository
        when(invoiceRepository.retrieveInvoicesBySupplier(any(Supplier.class))).thenReturn(sampleInvoices);

        // Execute the method to retrieve invoices associated with the supplier
        List<Invoice> invoices = iInvoiceService.getInvoicesBySupplier(supplierId);

        // Assertions
        assertNotNull(invoices);
        assertEquals(2, invoices.size());
        // Add other assertions for other properties as needed
    }

    @Test
    @Order(5)
    void assignOperatorToInvoice() {
        // Create a new operator
        Operator operator = new Operator();
        operator.setFname("fatma"); // Set the operator's name

        // Create a new invoice
        Invoice invoice = new Invoice();

        // Mock the behavior of the OperatorRepository
        when(operatorRepository.findById(any(Long.class))).thenReturn(Optional.of(operator));

        // Mock the behavior of the InvoiceRepository
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);

        // Execute the method to assign the operator to the invoice
        iInvoiceService.assignOperatorToInvoice(operator.getIdOperateur(), invoice.getIdInvoice());

        // Assertions
        assertNotNull(operator.getInvoices());
        assertTrue(operator.getInvoices().contains(invoice));
    }

    @Test
    @Order(6)
    void getTotalAmountInvoiceBetweenDates() {
        // Define start and end dates for the test
        Date startDate = createDate("2023-01-01");
        Date endDate = createDate("2023-02-01");

        // Mock the behavior of the InvoiceRepository
        when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn(1000.0f);

        // Execute the method to retrieve the total amount
        float totalAmount = iInvoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        // Assertions
        assertNotNull(totalAmount);
        assertEquals(1000.0f, totalAmount, 0.01);
    }

    // Utility method to create Date objects from date strings
    private Date createDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            // Handle any parsing exceptions
            throw new RuntimeException("Error parsing date string: " + dateString, e);
        }
    }
}
