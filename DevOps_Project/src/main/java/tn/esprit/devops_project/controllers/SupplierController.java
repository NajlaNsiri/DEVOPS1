package tn.esprit.devops_project.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devops_project.dto.SupplierDTO;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.services.iservices.IActivitySector;
import tn.esprit.devops_project.services.iservices.ISupplierService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@AllArgsConstructor
public class SupplierController {

    ISupplierService supplierService;
    IActivitySector activitySectorservice;

    @GetMapping("/supplier")
    public List<Supplier> getSuppliers() {
        return supplierService.retrieveAllSuppliers();
    }

    @GetMapping("/supplier/{supplierId}")
    public Supplier retrieveSupplier(@PathVariable Long supplierId) {
        return supplierService.retrieveSupplier(supplierId);
    }
    @DeleteMapping("/supplier/{supplierId}")
    public void removeFournisseur(@PathVariable Long supplierId) {
        supplierService.deleteSupplier(supplierId);
    }
    @PostMapping("/suppliers")
    public Supplier createSupplier(@RequestBody SupplierDTO supplierDTO) {
        Supplier supplier = mapSupplierDTOToSupplier(supplierDTO);
        return supplierService.addSupplier(supplier);
    }

    @PutMapping("/suppliers/{id}")
    public Supplier updateSupplier(@PathVariable Long id, @RequestBody SupplierDTO supplierDTO) {
        Supplier existingSupplier = supplierService.retrieveSupplier(id);
        Set<ActivitySector> activitySectors = mapActivitySectorIdsToActivitySectors(supplierDTO.getActivitySectorIds());
        existingSupplier.setActivitySectors(activitySectors);
        mapSupplierDTOToSupplier(existingSupplier, supplierDTO);
        return supplierService.updateSupplier(existingSupplier);
    }

    private Set<ActivitySector> mapActivitySectorIdsToActivitySectors(Set<Long> activitySectorIds) {
        Set<ActivitySector> activitySectors = new HashSet<>();
        for (Long activitySectorId : activitySectorIds) {
            ActivitySector activitySector = activitySectorservice.retrieveActivitySector(activitySectorId);
            activitySectors.add(activitySector);
        }
        return activitySectors;
    }

    private Supplier mapSupplierDTOToSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier();
        mapSupplierDTOToSupplier(supplier, supplierDTO);
        return supplier;
    }

    private void mapSupplierDTOToSupplier(Supplier supplier, SupplierDTO supplierDTO) {
        supplier.setCode(supplierDTO.getCode());
        supplier.setLabel(supplierDTO.getLabel());
        supplier.setSupplierCategory(supplierDTO.getSupplierCategory());
    }

}
