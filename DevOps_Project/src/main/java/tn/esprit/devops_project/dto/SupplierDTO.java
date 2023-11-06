package tn.esprit.devops_project.dto;

import lombok.Getter;
import lombok.Setter;
import tn.esprit.devops_project.entities.SupplierCategory;

import java.util.Set;

@Getter @Setter
public class  SupplierDTO {
    private String code;
    private String label;
    private SupplierCategory supplierCategory;
    private Set<Long> activitySectorIds;
}
