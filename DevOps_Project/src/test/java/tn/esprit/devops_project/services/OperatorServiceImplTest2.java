package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class OperatorServiceImplTest2 {

    @Autowired
    OperatorServiceImpl serviceop;
    @Autowired
    OperatorRepository repoOp;


    /************************* TEST UNIT *****************************/

    @Test
    void testAddOperator() {
        Operator newOperator = new Operator();
        newOperator.setFname("Nour");
        newOperator.setLname("BenMehrez");
        newOperator.setPassword("123");

        Operator addedOperator = serviceop.addOperator(newOperator);
        assertNotNull(addedOperator);

        Operator retrievedOperator = repoOp.findById(addedOperator.getIdOperateur()).orElse(null);
        assertNotNull(retrievedOperator);
        assertEquals("Nour", retrievedOperator.getFname());
        assertEquals("BenMehrez", retrievedOperator.getLname());
        assertEquals("123", retrievedOperator.getPassword());
    }

    @Test
    void testRetrieveAllOperators() {
        ArrayList<Operator> operatorList = new ArrayList<>();
        repoOp.saveAll(operatorList);
        List<Operator> actualRetrieveAllOperatorsResult = serviceop.retrieveAllOperators();
        assertNotSame(operatorList, actualRetrieveAllOperatorsResult);
        assertFalse(actualRetrieveAllOperatorsResult.isEmpty());
    }


    @Test
    void testDeleteOperator() {
        Operator newOperator = new Operator();
        newOperator.setFname("devops");

        Operator addedOperator = repoOp.save(newOperator);

        serviceop.deleteOperator(addedOperator.getIdOperateur());

        Optional<Operator> deletedOperator = repoOp.findById(addedOperator.getIdOperateur());

        assertFalse(deletedOperator.isPresent());
    }

    @Test
    void testUpdateOperator() {
        Operator newOperator = new Operator();
        newOperator.setFname("devops2");

        Operator addedOperator = repoOp.save(newOperator);

        addedOperator.setFname("najla");

        Operator updatedOperator = serviceop.updateOperator(addedOperator);

        Operator retrievedOperator = repoOp.findById(addedOperator.getIdOperateur()).orElse(null);

        assertEquals("najla", retrievedOperator.getFname());
    }

    @Test
    void testRetrieveOperator() {
        Operator newOperator = new Operator();
        newOperator.setFname("nom1");
        newOperator.setLname("lnom2");
        newOperator.setPassword("0000");

        Operator addedOperator = serviceop.addOperator(newOperator);

        Operator retrievedOperator = serviceop.retrieveOperator(addedOperator.getIdOperateur());

        assertNotNull(retrievedOperator);

        assertEquals("nom1", retrievedOperator.getFname());
        assertEquals("lnom1", retrievedOperator.getLname());
        assertEquals("0000", retrievedOperator.getPassword());
    }
}
