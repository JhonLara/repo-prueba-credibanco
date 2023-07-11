package com.prueba.credibanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.credibanco.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}
