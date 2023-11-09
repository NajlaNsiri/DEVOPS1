package tn.esprit.devops_project.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.Optional;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)

class OperatorServiceImplTest {

    @InjectMocks
    OperatorServiceImpl serviceop;

    @Mock
    OperatorRepository repoOp;

    @Test
    void retrieveAllOperators() {
        List<Operator> operatorList = new ArrayList<>();
        Mockito.when(repoOp.findAll()).thenReturn(operatorList);
        OperatorServiceImpl operatorService = new OperatorServiceImpl(repoOp);
        List<Operator> result = serviceop.retrieveAllOperators();
        assertEquals(operatorList, result);
    }

    @Test
    void addOperator() {
        Operator operator = new Operator();
        when(repoOp.save(operator)).thenReturn(operator);
        assertEquals(operator, serviceop.addOperator(operator));
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