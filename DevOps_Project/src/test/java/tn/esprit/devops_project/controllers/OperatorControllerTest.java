package tn.esprit.devops_project.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.dto.OperatorDTO;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.services.iservices.IOperatorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OperatorControllerTest {

    @InjectMocks
    private OperatorController operatorController;

    @Mock
    private IOperatorService operatorService;

    @Test
    void testRemoveOperator() {
        Long operatorId = 1L;

        // Mock the deleteOperator method to do nothing
        Mockito.doNothing().when(operatorService).deleteOperator(operatorId);

        // Call the controller method
        operatorController.removeOperator(operatorId);

        // Verify that deleteOperator was called with the correct argument
        Mockito.verify(operatorService, Mockito.times(1)).deleteOperator(operatorId);
    }

    @Test
    void testUpdateOperator() {
        Long operatorId = 1L;
        OperatorDTO operatorDTO = new OperatorDTO();
        operatorDTO.setFname("Updated First Name");
        operatorDTO.setLname("Updated Last Name");

        Operator existingOperator = createSampleOperatorWithId(operatorId);

        // Mock the service methods
        Mockito.when(operatorService.retrieveOperator(operatorId)).thenReturn(existingOperator);
        Mockito.when(operatorService.updateOperator(Mockito.any(Operator.class))).thenReturn(existingOperator);

        // Call the controller method
        Operator updatedOperator = operatorController.updateOperator(operatorId, operatorDTO);

        assertNotNull(updatedOperator);
        assertEquals("Updated First Name", updatedOperator.getFname());
        assertEquals("Updated Last Name", updatedOperator.getLname());
    }

    private Operator createSampleOperatorWithId(Long operatorId) {
        Operator operator = new Operator();
        operator.setIdOperateur(operatorId);
        operator.setFname("John");
        operator.setLname("Doe");
        operator.setPassword("password123");
        return operator;
    }
}
