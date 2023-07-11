package com.prueba.credibanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.credibanco.dto.BankDTO;
import com.prueba.credibanco.service.CardService;

@RestController
@RequestMapping(value = "/card")
public class CardController {

	@Autowired
	private CardService cardService;

	@GetMapping("/{productId}/number")
	public Long getCardNumber(@PathVariable(name = "productId") Long productId) {
		return cardService.getCardNumber(productId);
	}

	@PostMapping("/enroll")
	public void enroll(@RequestBody BankDTO infoBank) {
		cardService.enroll(infoBank);
	}

	@DeleteMapping("/{cardId}")
	public void lockedCard(@PathVariable(name = "cardId") Long cardId) {
		cardService.lockedCard(cardId);
	}

	@PostMapping("/balance")
	public void balance(@RequestBody BankDTO infoBank) {
		cardService.balance(infoBank);
	}

	@GetMapping("/balance/{cardId}")
	public Long getBalance(@PathVariable(name = "cardId") Long cardId) {
		return cardService.getBalance(cardId);
	}

}
