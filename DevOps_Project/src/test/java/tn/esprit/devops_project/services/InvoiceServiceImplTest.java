package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class InvoiceServiceImplTest {
    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    InvoiceServiceImpl invoiceService ;
    @Test
    void retrieveAllInvoices() {
        List<Invoice> actualInvoices = createSampleInvoices();

        when(invoiceRepository.findAll()).thenReturn(actualInvoices);

        List<Invoice> invoices = invoiceService.retrieveAllInvoices();
        assertNotNull(invoices);
        assertEquals(actualInvoices.size(), invoices.size());
    }
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
}