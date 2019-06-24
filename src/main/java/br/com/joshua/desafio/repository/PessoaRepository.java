package br.com.joshua.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joshua.desafio.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
