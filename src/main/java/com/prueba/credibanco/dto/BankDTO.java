package com.prueba.credibanco.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class BankDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long cardId;
	private Long balance;
	private Long price;
	private Long transactionId;
	private String owner;
	private String cardType;

}
