package br.com.rodrigo.algamoneyapi.repository;

import br.com.rodrigo.algamoneyapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
