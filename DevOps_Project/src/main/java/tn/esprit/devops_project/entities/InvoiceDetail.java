package tn.esprit.devops_project.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "InvoiceDetail")
public class InvoiceDetail {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idInvoiceDetail;
	int quantity;
	float price;
	@ManyToOne
	Product product;
	@ManyToOne
	@JsonIgnore
	Invoice invoice;

}
