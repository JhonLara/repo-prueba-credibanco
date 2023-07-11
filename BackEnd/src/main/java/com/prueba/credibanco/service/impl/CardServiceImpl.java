package com.prueba.credibanco.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.credibanco.dto.BankDTO;
import com.prueba.credibanco.dto.CardDTO;
import com.prueba.credibanco.entity.BankException;
import com.prueba.credibanco.entity.Card;
import com.prueba.credibanco.repository.CardRepository;
import com.prueba.credibanco.service.CardService;

/**
 * Clase encargada de realizar las operaciones de la tarjeta en la app
 * 
 * @author Jhon Lara
 *
 *
 */
@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepository cardRepository;

	/**
	 * Constantes con las cadenas de mensaje para mostrar al usuario
	 */
	public static final String CARD_NOT_FOUND = "La tarjeta con este # no existe";

	public static final String CARD_FOUND = "La tarjeta con este # ya existe";

	@Override
	public Long getCardNumber(Long productId) {
		Card card = new Card();
		Long numberCard = Long.parseLong("" + productId + new Random().nextLong(0L, 9999999999L));
		try {
			Optional<Card> cardOld = cardRepository.findById(numberCard);
			Card cardFound = cardOld.get();
			throw new BankException(CARD_FOUND);
		} catch (NoSuchElementException nse) {
			card.setCardId(numberCard);
			card.setState("INACTIVE");
			cardRepository.save(card);
			return card.getCardId();
		}

	}

	@Override
	public void enroll(BankDTO infoBank) {
		try {
			Optional<Card> card = cardRepository.findById(infoBank.getCardId());
			Card cardFound = card.get();
			cardFound.setExpirationDate(LocalDate.now().plusYears(3));
			cardFound.setBalance(0L);
			cardFound.setNameOwner(infoBank.getOwner());
			cardFound.setCardType(infoBank.getCardType());
			cardFound.setState("ACTIVE");
			cardRepository.save(cardFound);
		} catch (NoSuchElementException nse) {
			throw new BankException(CARD_NOT_FOUND);
		}

	}

	@Override
	public void lockedCard(Long cardId) {
		try {
			Optional<Card> card = cardRepository.findById(cardId);
			Card cardFound = card.get();
			cardFound.setState("LOCKED");
			cardRepository.save(cardFound);
		} catch (NoSuchElementException nse) {
			throw new BankException(CARD_NOT_FOUND);
		}
	}

	@Override
	public void balance(BankDTO infoBank) {
		try {
			Optional<Card> card = cardRepository.findById(infoBank.getCardId());
			Card cardFound = card.get();
			cardFound.setBalance(cardFound.getBalance() + infoBank.getBalance());
			cardRepository.save(cardFound);
		} catch (NoSuchElementException nse) {
			throw new BankException(CARD_NOT_FOUND);
		}

	}

	@Override
	public Long getBalance(Long cardId) {
		try {
			Optional<Card> card = cardRepository.findById(cardId);
			Card cardFound = card.get();
			return cardFound.getBalance();
		} catch (NoSuchElementException nse) {
			throw new BankException(CARD_NOT_FOUND);
		}

	}

	@Override
	public List<CardDTO> getCardList() {
		List<Card> cardList = cardRepository.findAll();
		List<CardDTO> cardReturnList = new ArrayList<>();
		cardList.forEach((card) -> cardReturnList.add(new CardDTO(card.getCardId(), card.getNameOwner(),
				card.getCardType(), card.getExpirationDate(), card.getBalance(), card.getState())));
		return cardReturnList;
	}

}
