package com.prueba.credibanco.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad encargada de modelar los datos de la tarjeta
 * 
 * @author Jhon Lara
 *
 */
@Entity
@Data
@Table(name = "CARD")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Card {

	@Id
	@Column(name = "ID_CARD")
	private Long cardId;

	@Column(name = "NAME_OWNER")
	private String nameOwner;

	@Column(name = "CARD_TYPE")
	private String cardType;

	@Column(name = "EXPIRATION_DATE")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDate expirationDate;

	@Column(name = "BALANCE")
	private Long balance;

	@Column(name = "STATE")
	private String state;

}
