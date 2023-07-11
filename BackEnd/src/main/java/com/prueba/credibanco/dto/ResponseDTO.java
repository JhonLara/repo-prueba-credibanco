package com.prueba.credibanco.dto;

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
public class ResponseDTO {

	private String message;
	private TransactionDTO transactionDTO;
	private Long cardId;
	private Long balance;

}
