package tn.esprit.devops_project.services;

//import static org.mockito.when;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.Optional;
import java.util.ArrayList;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)

class OperatorServiceImplTest {
    //@Autowired
    @InjectMocks
    OperatorServiceImpl serviceop;

    @Mock
   // @Autowired
    OperatorRepository repoOp;



    @Test
    //@Order(1)
    void retrieveAllOperators() {
        List<Operator> operatorList = new ArrayList<>();
        Mockito.when(repoOp.findAll()).thenReturn(operatorList);
        OperatorServiceImpl operatorService = new OperatorServiceImpl(repoOp);
        List<Operator> result = serviceop.retrieveAllOperators();
        assertEquals(operatorList, result);
    }

    @Test
    void addOperator() {
        /*Operator operatorAdd = new Operator().builder().title("test").build();
        when(op.findById(idOperateur)).thenReturn(Optional.of(Operator));
        Operator result= service.retrieveOperator(idOperateur);
        assertNotNull(result);
        verify(repoOp, times(1)).findById(idOperateur);*/
        Operator operatorToAdd = new Operator();
        operatorToAdd.setFname("Nour");
        operatorToAdd.setLname("benmehrez");
        operatorToAdd.setPassword("test");
        Operator operatorResult = serviceop.addOperator(operatorToAdd);

        assertThat(operatorResult).isNotNull();
        assertThat(operatorResult.getIdOperateur()).isNotNull();
        assertThat(operatorResult.getFname()).isEqualTo("Nour");
        assertThat(operatorResult.getLname()).isEqualTo("benmehrez");
        assertThat(operatorResult.getPassword()).isEqualTo("test");

        Operator operatorFromDatabase= repoOp.findById(operatorResult.getIdOperateur()).orElse(null);
        assertThat(operatorFromDatabase).isNotNull();

        assertThat(operatorFromDatabase.getFname()).isEqualTo("Nour");
        assertThat(operatorFromDatabase.getLname()).isEqualTo("benmehrez");
        assertThat(operatorFromDatabase.getPassword()).isEqualTo("test");
    }

    @Test
    void deleteOperator() {
      /* Operator operatorToDelete = new Operator();
        operatorToDelete.setIdOperateur(1L);
        // Appeler la méthode deleteOperator() pour supprimer l'objet créé précédemment
        serviceopM.deleteOperator(operatorToDelete.getIdOperateur());
        // Vérifier que l'objet a été supprimé en tentant de le récupérer à partir de l'ID
        Optional<Operator> deletedOperator = repoOpM.findById(operatorToDelete.getIdOperateur());
        assertFalse(deletedOperator.isPresent());*/
        Operator operator = new Operator();
        serviceop.deleteOperator(operator.getIdOperateur());

    }

    @Test
    void updateOperator() {
        Operator operator = new Operator();
        when(repoOp.save(operator)).thenReturn(operator);
        assertEquals(operator, serviceop.updateOperator(operator));

       /* Operator operator = new Operator();
        //operator.setIdOperateur(1L);
        operator.setLname("John Doe");
        // Appel de la méthode à tester
       // Operator updatedOperator = serviceopM.updateOperator(operator);
        // Vérification que l'objet a été mis à jour correctement
        assertEquals(operator, serviceopM.updateOperator(operator));
        // Vérification que l'objet a été enregistré dans le repository
        verify(repoOpM, times(1)).save(operator);*/
    }

    @Test
    void retrieveOperator() {

        /*Long operatorId = 1L;
        Operator operator = new Operator();
        operator.setIdOperateur(operatorId);
        Mockito.when(repoOpM.findById(operatorId)).thenReturn(Optional.of(operator));
        Operator retrievedOperator = serviceopM.retrieveOperator(operatorId);
        Assertions.assertEquals(operator, retrievedOperator);
*/

        Operator operator = new Operator();
        when(repoOp.findById(operator.getIdOperateur())).thenReturn(Optional.of(operator));
        assertEquals(operator.getIdOperateur(),
                serviceop.retrieveOperator(operator.getIdOperateur()).getIdOperateur());
    }
}