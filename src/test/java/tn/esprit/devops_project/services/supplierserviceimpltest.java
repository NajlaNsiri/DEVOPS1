package tn.esprit.devops_project.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.controllers.SupplierController;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.services.Iservices.ISupplierService;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class supplierserviceimpltest {

    @InjectMocks
    private SupplierController supplierController;

    @Mock
    private ISupplierService supplierService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSuppliers() {
        List<Supplier> mockSuppliers = new ArrayList<>();
        mockSuppliers.add(new Supplier(1L, "Supplier1"));
        mockSuppliers.add(new Supplier(2L, "Supplier2"));

        Mockito.when(supplierService.retrieveAllSuppliers()).thenReturn(mockSuppliers);

        List<Supplier> response = supplierController.getSuppliers();

        assertEquals(mockSuppliers, response);
    }



}
