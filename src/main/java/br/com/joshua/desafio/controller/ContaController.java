package br.com.joshua.desafio.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joshua.desafio.domain.Conta;
import br.com.joshua.desafio.domain.Transacao;
import br.com.joshua.desafio.repository.ContaRepository;
import br.com.joshua.desafio.repository.TransacaoRepository;
import br.com.joshua.desafio.service.TransacaoService;
import br.com.joshua.desafio.util.ExecutionResponse;

@RestController
@RequestMapping("/rest/conta")
public class ContaController {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private TransacaoService transacaoService;

	@Autowired
	private TransacaoRepository transacaoRepository;

	private static final String CONTA_BLOQUEADA = "Conta Bloqueada";

	private static final String CONTA_INEXISTENTE = "Conta Inexistente";

	@PostMapping("/criar")
	public HttpEntity criarConta(@RequestBody Conta conta) {

		ExecutionResponse response = new ExecutionResponse();

		try {
			conta.setDataCriacao(new Date());
			Conta contaRetorno = contaRepository.save(conta);

			response.setResult(contaRetorno);
			response.setStatus(ExecutionResponse.Status.SUCCESS);

			HttpEntity<Object> entity = new HttpEntity<Object>(response);

			return entity;
		} catch (Exception e) {
			response.setStatus(ExecutionResponse.Status.FAIL);
			response.setMessage(e.getMessage());
			HttpEntity<Object> entity = new HttpEntity<Object>(response);
			return entity;
		}

	}

	@PostMapping("/deposito")
	public HttpEntity deposito(@RequestBody Transacao transacao) {

		ExecutionResponse response = new ExecutionResponse();

		try {

			Conta conta = contaRepository.findOne(transacao.getConta().getId());

			if (conta == null) {
				throw new Exception(CONTA_INEXISTENTE);
			}

			if (!conta.getFlagAtivo()) {
				throw new Exception(CONTA_BLOQUEADA);
			}

			Transacao transacaoRetorno = transacaoService.deposito(transacao);

			response.setResult(transacaoRetorno);
			response.setStatus(ExecutionResponse.Status.SUCCESS);

			HttpEntity<Object> entity = new HttpEntity<Object>(response);

			return entity;
		} catch (Exception e) {
			response.setStatus(ExecutionResponse.Status.FAIL);
			response.setMessage(e.getMessage());
			HttpEntity<Object> entity = new HttpEntity<Object>(response);
			return entity;
		}

	}

	@PostMapping("/saque")
	public HttpEntity saque(@RequestBody Transacao transacao) {

		ExecutionResponse response = new ExecutionResponse();

		try {
			Conta conta = contaRepository.findOne(transacao.getConta().getId());

			if (conta == null) {
				throw new Exception(CONTA_INEXISTENTE);
			}

			if (!conta.getFlagAtivo()) {
				throw new Exception(CONTA_BLOQUEADA);
			}

			Transacao transacaoRetorno = transacaoService.saque(transacao);

			response.setResult(transacaoRetorno);
			response.setStatus(ExecutionResponse.Status.SUCCESS);

			HttpEntity<Object> entity = new HttpEntity<Object>(response);

			return entity;
		} catch (Exception e) {
			response.setStatus(ExecutionResponse.Status.FAIL);
			response.setMessage(e.getMessage());
			HttpEntity<Object> entity = new HttpEntity<Object>(response);
			return entity;
		}

	}

	@GetMapping("/saldo/{idConta}")
	public HttpEntity saldo(@PathVariable("idConta") Integer idConta) {

		ExecutionResponse response = new ExecutionResponse();

		try {
			Conta conta = contaRepository.findOne(idConta);

			if (conta == null) {
				throw new Exception(CONTA_INEXISTENTE);
			}

			if (!conta.getFlagAtivo()) {
				throw new Exception(CONTA_BLOQUEADA);
			}

			response.setResult(String.format("Saldo = %.2f", conta.getSaldo()));
			response.setStatus(ExecutionResponse.Status.SUCCESS);

			HttpEntity<Object> entity = new HttpEntity<Object>(response);

			return entity;
		} catch (Exception e) {
			response.setStatus(ExecutionResponse.Status.FAIL);
			response.setMessage(e.getMessage());
			HttpEntity<Object> entity = new HttpEntity<Object>(response);
			return entity;
		}

	}

	@GetMapping("/bloqueio/{idConta}")
	public HttpEntity bloqueioConta(@PathVariable("idConta") Integer idConta) {

		ExecutionResponse response = new ExecutionResponse();

		try {
			Conta conta = contaRepository.findOne(idConta);

			if (conta == null) {
				throw new Exception(CONTA_INEXISTENTE);
			}

			if (!conta.getFlagAtivo()) {
				throw new Exception(CONTA_BLOQUEADA);
			}

			conta.setFlagAtivo(Boolean.FALSE);

			conta = contaRepository.save(conta);

			response.setResult(conta);
			response.setStatus(ExecutionResponse.Status.SUCCESS);

			HttpEntity<Object> entity = new HttpEntity<Object>(response);

			return entity;
		} catch (Exception e) {
			response.setStatus(ExecutionResponse.Status.FAIL);
			response.setMessage(e.getMessage());
			HttpEntity<Object> entity = new HttpEntity<Object>(response);
			return entity;
		}

	}

	@GetMapping("/extrato/{idConta}")
	public HttpEntity extratoConta(@PathVariable("idConta") Integer idConta) {

		ExecutionResponse response = new ExecutionResponse();

		try {
			Conta conta = contaRepository.findOne(idConta);

			if (conta == null) {
				throw new Exception(CONTA_INEXISTENTE);
			}

			if (!conta.getFlagAtivo()) {
				throw new Exception(CONTA_BLOQUEADA);
			}

			List<Transacao> extrato = transacaoRepository.findByConta(conta);

			response.setResult(extrato);
			response.setStatus(ExecutionResponse.Status.SUCCESS);

			HttpEntity<Object> entity = new HttpEntity<Object>(response);

			return entity;
		} catch (Exception e) {
			response.setStatus(ExecutionResponse.Status.FAIL);
			response.setMessage(e.getMessage());
			HttpEntity<Object> entity = new HttpEntity<Object>(response);
			return entity;
		}

	}

	@GetMapping("/extrato/{idConta}/{dataInicio}/{dataFim}")
	public HttpEntity extratoConta(@PathVariable("idConta") Integer idConta,
			@PathVariable("dataInicio") String dataInicio, @PathVariable("dataFim") String dataFim) {

		ExecutionResponse response = new ExecutionResponse();

		try {
			Conta conta = contaRepository.findOne(idConta);

			if (conta == null) {
				throw new Exception(CONTA_INEXISTENTE);
			}

			if (!conta.getFlagAtivo()) {
				throw new Exception(CONTA_BLOQUEADA);
			}

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy_HH:mm:ss");

			List<Transacao> extrato = transacaoRepository.findByDataTransacaoBetween(dateFormat.parse(dataInicio),
					dateFormat.parse(dataFim));

			response.setResult(extrato);
			response.setStatus(ExecutionResponse.Status.SUCCESS);

			HttpEntity<Object> entity = new HttpEntity<Object>(response);

			return entity;
		} catch (Exception e) {
			response.setStatus(ExecutionResponse.Status.FAIL);
			response.setMessage(e.getMessage());
			HttpEntity<Object> entity = new HttpEntity<Object>(response);
			return entity;
		}

	}

}
