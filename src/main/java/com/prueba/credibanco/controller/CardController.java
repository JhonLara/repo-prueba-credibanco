package com.prueba.credibanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.credibanco.dto.BankDTO;
import com.prueba.credibanco.dto.ResponseDTO;
import com.prueba.credibanco.entity.BankException;
import com.prueba.credibanco.service.CardService;

@RestController
@RequestMapping(value = "/card")
public class CardController {

	@Autowired
	private CardService cardService;

	@GetMapping("/{productId}/number")
	public ResponseEntity<ResponseDTO> getCardNumber(@PathVariable(name = "productId") Long productId) {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setCardId(cardService.getCardNumber(productId));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (BankException exc) {
			response.setMessage(exc.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@PostMapping("/enroll")
	public ResponseEntity<String> enroll(@RequestBody BankDTO infoBank) {
		try {
			cardService.enroll(infoBank);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (BankException exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
		}
	}

	@DeleteMapping("/{cardId}")
	public ResponseEntity<String> lockedCard(@PathVariable(name = "cardId") Long cardId) {
		try {
			cardService.lockedCard(cardId);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (BankException exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
		}
	}

	@PostMapping("/balance")
	public ResponseEntity<String> balance(@RequestBody BankDTO infoBank) {
		try {
			cardService.balance(infoBank);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (BankException exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
		}
	}

	@GetMapping("/balance/{cardId}")
	public ResponseEntity<ResponseDTO> getBalance(@PathVariable(name = "cardId") Long cardId) {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setBalance(cardService.getBalance(cardId));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (BankException exc) {
			response.setMessage(exc.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

}
