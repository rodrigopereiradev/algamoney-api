package br.com.rodrigo.algamoneyapi.repository;

import br.com.rodrigo.algamoneyapi.model.Lancamento;
import br.com.rodrigo.algamoneyapi.repository.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepsitory extends JpaRepository<Lancamento, Long> {
}
