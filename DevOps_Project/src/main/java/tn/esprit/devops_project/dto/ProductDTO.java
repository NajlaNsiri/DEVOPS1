package tn.esprit.devops_project.dto;

import lombok.Data;
import tn.esprit.devops_project.entities.ProductCategory;

@Data
public class ProductDTO {
    private String title;
    private float price;
    private int quantity;
    private ProductCategory category;
    private Long idStock;
}
