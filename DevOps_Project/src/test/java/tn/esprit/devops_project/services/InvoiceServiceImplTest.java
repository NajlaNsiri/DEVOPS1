package tn.esprit.devops_project.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.mock.mockito.MockBean;
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
//@TestMethodOrder(OrderAnnotation.class)
class InvoiceServiceImplTest {

    @Autowired
    IInvoiceService iInvoiceService;

    @MockBean
    private OperatorRepository operatorRepository;

    @MockBean
    private InvoiceRepository invoiceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void retrieveAllInvoices() {
        List<Invoice> actualInvoices = createSampleInvoices(); // Replace with your actual data

        // Mock the behavior of the InvoiceRepository to return the actual invoices
        when(invoiceRepository.findAll()).thenReturn(actualInvoices);

        List<Invoice> invoices = iInvoiceService.retrieveAllInvoices();
        assertNotNull(invoices);
        assertEquals(actualInvoices.size(), invoices.size());
    }

    @Test
    void cancelInvoice() {
        Long invoiceId = 1L;

        Invoice originalInvoice = createSampleInvoice(); // Replace with your actual data

        // Mock the behavior of the InvoiceRepository to return the original invoice
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(originalInvoice));

        iInvoiceService.cancelInvoice(invoiceId);

        // Verify that the repository's update method is called
        verify(invoiceRepository).updateInvoice(eq(invoiceId));
    }

    @Test
    void retrieveInvoice() {
        Long invoiceIdToRetrieve = 1L;

        Invoice actualInvoice = createSampleInvoice(); // Replace with your actual data

        // Mock the behavior of the InvoiceRepository to return the actual invoice
        when(invoiceRepository.findById(eq(invoiceIdToRetrieve))).thenReturn(Optional.of(actualInvoice));

        Invoice retrievedInvoice = iInvoiceService.retrieveInvoice(invoiceIdToRetrieve);
        assertNotNull(retrievedInvoice);
        assertEquals(actualInvoice.getIdInvoice(), retrievedInvoice.getIdInvoice());
        assertEquals(actualInvoice.getAmountDiscount(), retrievedInvoice.getAmountDiscount(), 0.01);
        assertEquals(actualInvoice.getAmountInvoice(), retrievedInvoice.getAmountInvoice(), 0.01);
        assertEquals(actualInvoice.getArchived(), retrievedInvoice.getArchived());

        // Add more property validations as needed.
    }

    // Add other test methods following a similar pattern.

    private List<Invoice> createSampleInvoices() {
        List<Invoice> invoices = new ArrayList<>();

        // Create a few sample invoices
        Invoice invoice1 = new Invoice();
        invoice1.setIdInvoice(1L);
        invoice1.setAmountDiscount(100.0f);
        invoice1.setAmountInvoice(500.0f);
        invoice1.setDateCreationInvoice(new Date()); // You can set the desired date
        invoice1.setDateLastModificationInvoice(new Date()); // You can set the desired date
        invoice1.setArchived(false);

        Invoice invoice2 = new Invoice();
        invoice1.setIdInvoice(2L);
        invoice2.setAmountDiscount(50.0f);
        invoice2.setAmountInvoice(300.0f);
        invoice2.setDateCreationInvoice(new Date()); // You can set the desired date
        invoice2.setDateLastModificationInvoice(new Date()); // You can set the desired date
        invoice2.setArchived(false);

        Invoice invoice3 = new Invoice();
        invoice1.setIdInvoice(3L);
        invoice3.setAmountDiscount(75.0f);
        invoice3.setAmountInvoice(700.0f);
        invoice3.setDateCreationInvoice(new Date()); // You can set the desired date
        invoice3.setDateLastModificationInvoice(new Date()); // You can set the desired date
        invoice3.setArchived(false);

        invoices.add(invoice1);
        invoices.add(invoice2);
        invoices.add(invoice3);

        return invoices;
    }

    private Invoice createSampleInvoice() {
        // Create an actual invoice
        Invoice invoice = new Invoice();
        invoice.setIdInvoice(1L);
        invoice.setAmountDiscount(100.0f);
        invoice.setAmountInvoice(500.0f);
        invoice.setArchived(false);
        return invoice;
    }

    @Test
    void assignOperatorToInvoice() {
        // Create a new operator
        Operator operator = new Operator();
        operator.setFname("fatma");

        // Mock the operatorRepository save method
        Mockito.when(operatorRepository.save(Mockito.any(Operator.class))).thenReturn(operator);

        // Create a new invoice
        Invoice invoice = createSampleInvoices().get(0); // Use one of the sample invoices

        // Execute the method to assign the operator to the invoice
        iInvoiceService.assignOperatorToInvoice(operator.getIdOperateur(), invoice.getIdInvoice());

        // Verify that the operatorRepository save method was called with the correct operator
        Mockito.verify(operatorRepository).save(operator);

        // Verify that the operator is now associated with the invoice
        assertTrue(operator.getInvoices().contains(invoice));
    }
}
