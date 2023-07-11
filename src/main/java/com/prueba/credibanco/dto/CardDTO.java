package com.prueba.credibanco.dto;

import java.time.LocalDate;

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
public class CardDTO {

	private Long cardId;
	private String nameOwner;
	private String cardType;
	private LocalDate expirationDate;
	private Long balance;
	private String state;
}
