package com.prueba.credibanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.credibanco.dto.BankDTO;
import com.prueba.credibanco.dto.ResponseDTO;
import com.prueba.credibanco.entity.BankException;
import com.prueba.credibanco.service.TransactionService;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/purchase")
	public ResponseEntity<String> purchase(@RequestBody BankDTO infoBank) {
		try {
			transactionService.purchase(infoBank);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (BankException exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
		}
	}

	@GetMapping("/{transactionId}")
	public ResponseEntity<ResponseDTO> getTransaction(@PathVariable(name = "transactionId") Long transactionId) {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setTransactionDTO(transactionService.getTransaction(transactionId));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (BankException exc) {
			response.setMessage(exc.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@PostMapping("/anulation")
	public ResponseEntity<String> anulation(@RequestBody BankDTO infoBank) {
		try {
			transactionService.anulation(infoBank.getTransactionId());
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (BankException exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
		}
	}

}
