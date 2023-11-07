package tn.esprit.devops_project.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tn.esprit.devops_project.dto.OperatorDTO;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.services.iservices.IOperatorService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class OperatorControllerTest {
    @InjectMocks
    private OperatorController operatorController;

    @Mock
    private IOperatorService operatorService;

    @Test
    void testRemoveOperator() {
        Long operatorId = 1L;

        // Mock the deleteOperator method to do nothing
        doNothing().when(operatorService).deleteOperator(operatorId);

        // Call the controller method
        operatorController.removeOperator(1L);

        // Verify that deleteOperator was called with the correct argument
        verify(operatorService, times(1)).deleteOperator(operatorId);
    }

    @Test
    void testUpdateOperator() {
        Long operatorId = 1L;
        OperatorDTO operatorDTO = new OperatorDTO();
        operatorDTO.setFname("Updated First Name");
        operatorDTO.setLname("Updated Last Name");

        Operator existingOperator = createSampleOperatorWithId(operatorId);

        // Mock the service methods
        when(operatorService.retrieveOperator(1L)).thenReturn(existingOperator);
        when(operatorService.updateOperator(any(Operator.class))).thenReturn(existingOperator);

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
