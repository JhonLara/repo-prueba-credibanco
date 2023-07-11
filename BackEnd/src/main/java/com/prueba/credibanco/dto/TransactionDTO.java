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
public class TransactionDTO {

	private Long idTransaction;
	private LocalDate creationDate;
	private Long price;
	private String state;
	private CardDTO cardDTO;

}
