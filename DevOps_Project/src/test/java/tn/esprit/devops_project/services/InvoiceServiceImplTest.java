package tn.esprit.devops_project.services;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.iservices.IInvoiceService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class InvoiceServiceImplTest {

    @Autowired
    IInvoiceService iInvoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;
    @Mock
    private OperatorRepository operatorRepository;

    @Test
    void retrieveAllInvoices() {
        List<Invoice> actualInvoices = createSampleInvoices();

        when(invoiceRepository.findAll()).thenReturn(actualInvoices);

        List<Invoice> invoices = iInvoiceService.retrieveAllInvoices();
        assertNotNull(invoices);
        assertEquals(actualInvoices.size(), invoices.size());
    }

    @Test
    void cancelInvoice() {
        Long invoiceId = 1L;

        Invoice originalInvoice = createSampleInvoice();

        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(originalInvoice));

        iInvoiceService.cancelInvoice(invoiceId);

        verify(invoiceRepository).updateInvoice(invoiceId);
    }

    @Test
    void retrieveInvoice() {
        Long invoiceIdToRetrieve = 1L;

        Invoice actualInvoice = createSampleInvoice();

        when(invoiceRepository.findById(invoiceIdToRetrieve)).thenReturn(Optional.of(actualInvoice));

        Invoice retrievedInvoice = iInvoiceService.retrieveInvoice(invoiceIdToRetrieve);
        assertNotNull(retrievedInvoice);
        assertInvoicesEqual(actualInvoice, retrievedInvoice);
    }

    // Add other test methods following a similar pattern.

    private List<Invoice> createSampleInvoices() {
        List<Invoice> invoices = new ArrayList<>();

        // Create a few sample invoices
        Invoice invoice1 = new Invoice();
        invoice1.setAmountDiscount(100.0f);
        invoice1.setAmountInvoice(500.0f);
        invoice1.setDateCreationInvoice(new Date());
        invoice1.setDateLastModificationInvoice(new Date());
        invoice1.setArchived(false);

        Invoice invoice2 = new Invoice();
        invoice2.setAmountDiscount(50.0f);
        invoice2.setAmountInvoice(300.0f);
        invoice2.setDateCreationInvoice(new Date());
        invoice2.setDateLastModificationInvoice(new Date());
        invoice2.setArchived(false);

        Invoice invoice3 = new Invoice();
        invoice3.setAmountDiscount(75.0f);
        invoice3.setAmountInvoice(700.0f);
        invoice3.setDateCreationInvoice(new Date());
        invoice3.setDateLastModificationInvoice(new Date());
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

    private void assertInvoicesEqual(Invoice expected, Invoice actual) {
        assertEquals(expected.getIdInvoice(), actual.getIdInvoice());
        assertEquals(expected.getAmountDiscount(), actual.getAmountDiscount(), 0.01);
        assertEquals(expected.getAmountInvoice(), actual.getAmountInvoice(), 0.01);
        assertEquals(expected.getArchived(), actual.getArchived());
        // Add more assertions as needed for other fields
    }

    @Test
    void testAssignOperatorToInvoice() {
        // Arrange
        Long invoiceId = 1L;
        Long operatorId = 2L;

        Invoice originalInvoice = createSampleInvoice();
        Operator operator = createSampleOperator();

        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(originalInvoice));
        when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(operator));

        // Act
        iInvoiceService.assignOperatorToInvoice(operatorId, invoiceId);

        // Assert
        assertTrue(operator.getInvoices().contains(originalInvoice));
        verify(operatorRepository).save(operator);
    }
    private Operator createSampleOperator() {
        Operator operator = new Operator();
        operator.setIdOperateur(2L); // Set the operator's ID
        operator.setFname("John"); // Set the first name
        operator.setLname("Doe"); // Set the last name
        operator.setPassword("password123"); // Set the password

        // Create an empty set for invoices, as this operator doesn't have any initially.
        operator.setInvoices(new HashSet<>());

        return operator;
    }
}
