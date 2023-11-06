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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    void assignOperatorToInvoice() {
        Long idOperator = 1L;
        Long idInvoice = 2L;

        Operator operator = new Operator();
        operator.setIdOperateur(idOperator);

        Invoice invoice = new Invoice();
        invoice.setIdInvoice(idInvoice);

        // Mock the behavior of your repositories
        when(operatorRepository.findById(idOperator)).thenReturn(Optional.of(operator));
        when(invoiceRepository.findById(idInvoice)).thenReturn(Optional.of(invoice));

        // Call your service method
        iInvoiceService.assignOperatorToInvoice(idOperator, idInvoice);

        // Verify that the operator's invoices have been updated
        verify(operatorRepository, times(1)).save(operator);

        // Ensure that the invoice has been added to the operator's invoices
        assertTrue(operator.getInvoices().contains(invoice));
    }
    
}
