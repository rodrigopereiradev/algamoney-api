package br.com.rodrigo.algamoneyapi.service;

import br.com.rodrigo.algamoneyapi.model.Lancamento;
import br.com.rodrigo.algamoneyapi.model.Pessoa;
import br.com.rodrigo.algamoneyapi.repository.LancamentoRepsitory;
import br.com.rodrigo.algamoneyapi.repository.PessoaRepository;
import br.com.rodrigo.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepsitory lancamentoRepsitory;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
        if (pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }
        return lancamentoRepsitory.save(lancamento);
    }

}
