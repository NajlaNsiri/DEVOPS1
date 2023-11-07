package tn.esprit.devops_project.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Stock  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idStock;
    String title;
    @OneToMany(mappedBy = "stock")
    Set<Product> products;
}