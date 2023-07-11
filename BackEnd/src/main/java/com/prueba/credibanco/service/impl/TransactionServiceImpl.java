package com.prueba.credibanco.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.credibanco.dto.BankDTO;
import com.prueba.credibanco.dto.CardDTO;
import com.prueba.credibanco.dto.TransactionDTO;
import com.prueba.credibanco.entity.BankException;
import com.prueba.credibanco.entity.Card;
import com.prueba.credibanco.entity.Transaction;
import com.prueba.credibanco.repository.CardRepository;
import com.prueba.credibanco.repository.TransactionRepository;
import com.prueba.credibanco.service.TransactionService;

/**
 * Clase encargada de realizar las operaciones de las transacciones en la app
 * 
 * @author Jhon Lara
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private CardRepository cardRepository;

	/**
	 * Constantes con las cadenas de mensaje para mostrar al usuario
	 */
	public static final String CARD_NOT_FOUND = "La tarjeta con este # no existe";

	public static final String TRANSACTION_NOT_FOUND = "La transacción no existe";

	public static final String LOW_BALANCE = "Saldo insuficiente";

	public static final String NOT_ACTIVE = "Esta tarjeta no esta activa";

	public static final String TRANSACTION_OLD = "La transacción es mayor a 24 horas";

	@Override
	public TransactionDTO getTransaction(Long transactionId) throws BankException {
		try {
			Optional<Transaction> traOptional = transactionRepository.findById(transactionId);
			Transaction transactionFound = traOptional.get();
			CardDTO cardDTO = new CardDTO(transactionFound.getCard().getCardId(),
					transactionFound.getCard().getNameOwner(), transactionFound.getCard().getCardType(),
					transactionFound.getCard().getExpirationDate(), transactionFound.getCard().getBalance(),
					transactionFound.getCard().getState());

			return new TransactionDTO(transactionFound.getIdTransaction(), transactionFound.getCreationDate(),
					transactionFound.getPrice(), transactionFound.getState(), cardDTO);

		} catch (NoSuchElementException nse) {
			throw new BankException(TRANSACTION_NOT_FOUND);
		}

	}

	@Override
	public void purchase(BankDTO infoBank) throws BankException {
		try {
			Optional<Card> card = cardRepository.findById(infoBank.getCardId());
			Card cardFound = card.get();
			if (!cardFound.getState().equals("ACTIVE")) {
				throw new BankException(NOT_ACTIVE);
			} else if (cardFound.getBalance() < infoBank.getBalance()) {
				throw new BankException(LOW_BALANCE);
			} else {
				cardFound.setBalance(cardFound.getBalance() - infoBank.getBalance());
			}

			Transaction transaction = new Transaction(null, LocalDate.now(), infoBank.getBalance(), "CREATE",
					cardFound);
			transactionRepository.save(transaction);
		} catch (NoSuchElementException nse) {
			throw new BankException(CARD_NOT_FOUND);
		}

	}

	@Override
	public void anulation(Long transactionId) throws BankException {
		try {
			Optional<Transaction> traOptional = transactionRepository.findById(transactionId);
			Transaction transactionFound = traOptional.get();
			long noOfDaysBetween = ChronoUnit.DAYS.between(transactionFound.getCreationDate(), LocalDate.now());
			if (noOfDaysBetween > 1) {
				throw new BankException(TRANSACTION_OLD);
			}
			transactionFound.setState("CANCELED");
			transactionFound.getCard()
					.setBalance(transactionFound.getCard().getBalance() + transactionFound.getPrice());
			transactionRepository.save(transactionFound);
		} catch (NoSuchElementException nse) {
			throw new BankException(TRANSACTION_NOT_FOUND);
		}

	}

	@Override
	public List<TransactionDTO> getTransactionList() {
		List<Transaction> transactionList = transactionRepository.findAll();
		List<TransactionDTO> transactionDTOList = new ArrayList<>();

		transactionList
				.forEach((transaction) -> transactionDTOList.add(new TransactionDTO(transaction.getIdTransaction(),
						transaction.getCreationDate(), transaction.getPrice(), transaction.getState(),
						new CardDTO(transaction.getCard().getCardId(), transaction.getCard().getNameOwner(),
								transaction.getCard().getCardType(), transaction.getCard().getExpirationDate(),
								transaction.getCard().getBalance(), transaction.getCard().getState()))));

		return transactionDTOList;
	}

}
