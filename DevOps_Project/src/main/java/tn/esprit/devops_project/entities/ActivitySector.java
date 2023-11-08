package tn.esprit.devops_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "ActivitySector")
public class ActivitySector  {
        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long idSecteurActivite;
        String codeSecteurActivite;
        String libelleSecteurActivite;
        @ManyToMany(mappedBy="activitySectors")
        @JsonIgnore
        private Set<Supplier> suppliers;

}
