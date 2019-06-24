package br.com.joshua.desafio.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joshua.desafio.domain.Conta;
import br.com.joshua.desafio.domain.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

	List<Transacao> findByDataTransacaoBetween(Date dataInicio,Date DataFim);
	
	List<Transacao> findByConta(Conta conta);
	
}
