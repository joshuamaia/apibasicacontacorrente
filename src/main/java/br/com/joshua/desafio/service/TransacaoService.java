package br.com.joshua.desafio.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joshua.desafio.domain.Conta;
import br.com.joshua.desafio.domain.Transacao;
import br.com.joshua.desafio.repository.ContaRepository;
import br.com.joshua.desafio.repository.TransacaoRepository;

@Service
public class TransacaoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private TransacaoRepository transacaoRepository;

	@Autowired
	private ContaRepository contaRepository;

	public Transacao deposito(Transacao transacao) {

		transacao.setDataTransacao(new Date());
		Transacao transacaoRetorno = transacaoRepository.save(transacao);

		Conta conta = contaRepository.findOne(transacao.getConta().getId());

		conta.setSaldo(conta.getSaldo().add(transacao.getValor()));

		contaRepository.save(conta);

		return transacaoRetorno;

	}
	
	public Transacao saque(Transacao transacao) throws Exception {

		transacao.setDataTransacao(new Date());
		transacao.setValor(transacao.getValor().doubleValue() < 0 ? transacao.getValor()
				: transacao.getValor().multiply(new BigDecimal(-1)));

		Calendar hojeInicioDia = Calendar.getInstance();
		Calendar hojeFimDia = Calendar.getInstance();
		Calendar hoje = Calendar.getInstance();
		hojeInicioDia.set(hoje.get(Calendar.YEAR), hoje.get(Calendar.MONTH), hoje.get(Calendar.DAY_OF_MONTH), 00, 00,
				00);
		hojeFimDia.set(hoje.get(Calendar.YEAR), hoje.get(Calendar.MONTH), hoje.get(Calendar.DAY_OF_MONTH), 23, 59, 59);

		List<Transacao> transacoes = transacaoRepository.findByDataTransacaoBetween(hojeInicioDia.getTime(),
				hojeFimDia.getTime());

		BigDecimal saldoSaque = BigDecimal.ZERO;

		for (Transacao t : transacoes) {
			if (t.getValor().compareTo(BigDecimal.ZERO) < 0) {
				saldoSaque = saldoSaque.add(t.getValor());
			}
		}
		
		saldoSaque = saldoSaque.add(transacao.getValor());
		
		Conta conta = contaRepository.findOne(transacao.getConta().getId());
		
		BigDecimal limiteConta = conta.getLimiteSaqueDiario().multiply(new BigDecimal(-1));
		
		if (saldoSaque.compareTo(limiteConta) < 0) {
			throw new Exception("Valor ultrapassa o limite de saque diário!");
		} 

		Transacao transacaoRetorno = transacaoRepository.save(transacao);

		conta.setSaldo(conta.getSaldo().add(transacao.getValor()));

		contaRepository.save(conta);

		return transacaoRetorno;

	}
}
