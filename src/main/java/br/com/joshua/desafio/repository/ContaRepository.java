package br.com.joshua.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joshua.desafio.domain.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer> {

}
