package tn.esprit.devops_project.dto;

import lombok.Data;
import tn.esprit.devops_project.entities.SupplierCategory;
import java.util.Set;

@Data
public class SupplierDTO {
    private String code;
    private String label;
    private SupplierCategory supplierCategory;
    private Set<Long> activitySectorIds;
}
