package com.prueba.credibanco.service;

import java.util.List;

import com.prueba.credibanco.dto.BankDTO;
import com.prueba.credibanco.dto.TransactionDTO;
import com.prueba.credibanco.entity.BankException;

public interface TransactionService {

	TransactionDTO getTransaction(Long transactionId) throws BankException;

	void purchase(BankDTO infoBank) throws BankException;

	void anulation(Long transactionId) throws BankException;

	List<TransactionDTO> getTransactionList();

}
