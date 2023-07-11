package com.prueba.credibanco.service;

import com.prueba.credibanco.dto.BankDTO;

public interface CardService {

	Long getCardNumber(Long productId);

	void enroll(BankDTO infoBank);

	void lockedCard(Long cardId);

	void balance(BankDTO infoBank);

	Long getBalance(Long card);

}
