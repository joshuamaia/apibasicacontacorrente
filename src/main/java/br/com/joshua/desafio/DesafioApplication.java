package br.com.joshua.desafio;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.joshua.desafio.domain.Pessoa;
import br.com.joshua.desafio.repository.PessoaRepository;

@SpringBootApplication
public class DesafioApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DesafioApplication.class, args);

		PessoaRepository pessoaRepository = context.getBean(PessoaRepository.class);

		if (!pessoaRepository.exists(1)) {
			Pessoa pessoa = new Pessoa();
			pessoa.setCpf("83632205582");
			pessoa.setId(1);
			pessoa.setDataNascimento(new Date());
			pessoa.setNome("Pessoa 1");
			
			pessoaRepository.save(pessoa);
		}

		if (!pessoaRepository.exists(2)) {
			Pessoa pessoa = new Pessoa();
			pessoa.setCpf("20651186528");
			pessoa.setId(2);
			pessoa.setDataNascimento(new Date());
			pessoa.setNome("Pessoa 2");
			
			pessoaRepository.save(pessoa);
		}

		if (!pessoaRepository.exists(3)) {
			Pessoa pessoa = new Pessoa();
			pessoa.setCpf("56865163320");
			pessoa.setId(3);
			pessoa.setDataNascimento(new Date());
			pessoa.setNome("Pessoa 2");
			
			pessoaRepository.save(pessoa);
		}
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DesafioApplication.class);
	}
}
