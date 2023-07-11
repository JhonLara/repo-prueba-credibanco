package com.prueba.credibanco.service;

import java.util.List;

import com.prueba.credibanco.dto.BankDTO;
import com.prueba.credibanco.dto.CardDTO;

public interface CardService {

	Long getCardNumber(Long productId);

	void enroll(BankDTO infoBank);

	void lockedCard(Long cardId);

	void balance(BankDTO infoBank);

	Long getBalance(Long card);

	List<CardDTO> getCardList();

}
