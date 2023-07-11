package com.prueba.credibanco.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.prueba.credibanco.entity.Card;
import com.prueba.credibanco.repository.CardRepository;
import com.prueba.credibanco.service.impl.CardServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CardServiceTest {

	@InjectMocks
	CardServiceImpl cardService;

	@Mock
	CardRepository cardRepository;

	@Test
	public void getCardNumberTest() throws Exception {
		assertNotNull(cardService.getCardNumber(889900L));
	}

	@Test
	public void enrollTest() throws Exception {
		when(cardRepository.save(any(Card.class)));
	};

}
