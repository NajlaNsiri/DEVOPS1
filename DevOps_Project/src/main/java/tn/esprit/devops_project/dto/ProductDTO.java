package tn.esprit.devops_project.dto;

import lombok.Getter;
import lombok.Setter;
import tn.esprit.devops_project.entities.ProductCategory;

@Setter @Getter
public class ProductDTO {
    private String title;
    private float price;
    private int quantity;
    private ProductCategory category;
    private Long idStock;
}
