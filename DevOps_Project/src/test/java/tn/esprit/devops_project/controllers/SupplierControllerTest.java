package tn.esprit.devops_project.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.dto.SupplierDTO;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.services.iservices.ISupplierService;
import tn.esprit.devops_project.services.iservices.IActivitySector;

import java.util.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SupplierControllerTest {

    @InjectMocks
    private SupplierController supplierController;

    @Mock
    private ISupplierService supplierService;

    @Mock
    private IActivitySector activitySectorservice;

    @Test
    void testGetSuppliers() {
        // Mock the service method to return a list of suppliers
        List<Supplier> suppliers = Arrays.asList(createSampleSupplierWithId(1L), createSampleSupplierWithId(2L));
        Mockito.when(supplierService.retrieveAllSuppliers()).thenReturn(suppliers);

        // Call the controller method
        List<Supplier> result = supplierController.getSuppliers();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testRetrieveSupplier() {
        Long supplierId = 1L;
        Supplier supplier = createSampleSupplierWithId(supplierId);

        // Mock the service method to return a specific supplier
        Mockito.when(supplierService.retrieveSupplier(supplierId)).thenReturn(supplier);

        // Call the controller method
        Supplier result = supplierController.retrieveSupplier(supplierId);

        assertNotNull(result);
        assertEquals(supplierId, result.getIdSupplier());
    }

    @Test
    void testRemoveSupplier() {
        Long supplierId = 1L;

        // Mock the deleteSupplier method to do nothing
        Mockito.doNothing().when(supplierService).deleteSupplier(supplierId);

        // Call the controller method
        supplierController.removeFournisseur(supplierId);

        // Verify that deleteSupplier was called with the correct argument
        Mockito.verify(supplierService, Mockito.times(1)).deleteSupplier(supplierId);
    }

    @Test
    void testUpdateSupplier() {
        Long supplierId = 1L;
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setCode("Updated Code");
        supplierDTO.setLabel("Updated Label");

        Supplier existingSupplier = createSampleSupplierWithId(supplierId);

        // Mock the service methods
        Mockito.when(supplierService.retrieveSupplier(supplierId)).thenReturn(existingSupplier);
        Mockito.when(supplierService.updateSupplier(Mockito.any(Supplier.class))).thenReturn(existingSupplier);

        // Call the controller method
        Supplier updatedSupplier = supplierController.updateSupplier(supplierId, supplierDTO);

        assertNotNull(updatedSupplier);
        assertEquals("Updated Code", updatedSupplier.getCode());
        assertEquals("Updated Label", updatedSupplier.getLabel());
    }

    private Supplier createSampleSupplierWithId(Long supplierId) {
        Supplier supplier = new Supplier();
        supplier.setIdSupplier(supplierId);
        supplier.setCode("Sample Code");
        supplier.setLabel("Sample Label");
        // Set other properties as needed
        return supplier;
    }
}
