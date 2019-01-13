package br.com.rodrigo.algamoneyapi.repository.lancamento;

import br.com.rodrigo.algamoneyapi.model.Lancamento;
import br.com.rodrigo.algamoneyapi.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LancamentoRepositoryQuery {

    Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
}
