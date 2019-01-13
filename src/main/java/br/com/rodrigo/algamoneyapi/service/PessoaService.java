package br.com.rodrigo.algamoneyapi.service;

import br.com.rodrigo.algamoneyapi.model.Pessoa;
import br.com.rodrigo.algamoneyapi.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
        return pessoaRepository.save(pessoaSalva);
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Pessoa pessoa = buscarPessoaPeloCodigo(codigo);
        pessoa.setAtivo(ativo);
        pessoaRepository.save(pessoa);
    }

    public Pessoa buscarPessoaPeloCodigo(Long codigo) {
        Pessoa pessoa = pessoaRepository.findOne(codigo);
        if (pessoa == null)
            throw new EmptyResultDataAccessException(1);
        return pessoa;
    }
}
