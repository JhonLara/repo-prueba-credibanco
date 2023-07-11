package com.prueba.credibanco.entity;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad encargada de modelar los datos de la transacción
 * 
 * @author Jhon Lara
 *
 */
@Entity
@Table(name = "TRANSACTION")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {

	@Id
	@Column(name = "ID_TRANSACTION")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_entity_seq_gen")
	@SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "MY_ENTITY_SEQ", allocationSize = 1)
	private Long idTransaction;

	@Column(name = "CREATION_DATE")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDate creationDate;

	@Column(name = "PRICE")
	private Long price;

	@Column(name = "STATE")
	private String state;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "CARD_ID", referencedColumnName = "ID_CARD", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Card card;

}
