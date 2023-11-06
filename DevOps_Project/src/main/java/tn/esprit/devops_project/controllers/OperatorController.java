package tn.esprit.devops_project.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devops_project.dto.OperatorDTO;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.services.iservices.IOperatorService;

import java.util.List;

@RestController
@AllArgsConstructor
public class OperatorController {

	IOperatorService operatorService;
	
	@GetMapping("/operator")
	public List<Operator> getOperators() {
		return operatorService.retrieveAllOperators();
	}

	@GetMapping("/operator/{operatorId}")
	public Operator retrieveoperator(@PathVariable Long operatorId) {
		return operatorService.retrieveOperator(operatorId);
	}

	@PostMapping("/operators")
	public Operator createOperator(@RequestBody OperatorDTO operatorDTO) {
		Operator operator = new Operator();
		operator.setFname(operatorDTO.getFname());
		operator.setLname(operatorDTO.getLname());
		operator.setPassword(operatorDTO.getPassword());
		return operatorService.addOperator(operator);
	}

	@DeleteMapping("/operatot/{operatorId}")
	public void removeOperator(@PathVariable Long operatorId) {
		operatorService.deleteOperator(operatorId);
	}

	@PutMapping("/operators/{id}")
	public Operator updateOperator(@PathVariable Long id, @RequestBody OperatorDTO operatorDTO) {
		Operator existingOperator = operatorService.retrieveOperator(id);
		existingOperator.setFname(operatorDTO.getFname());
		existingOperator.setLname(operatorDTO.getLname());
		return operatorService.updateOperator(existingOperator);
	}

	
}
