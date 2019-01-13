package br.com.rodrigo.algamoneyapi.resource;

import br.com.rodrigo.algamoneyapi.evento.RecursoCriadoEvent;
import br.com.rodrigo.algamoneyapi.exceptionhandler.Erro;
import br.com.rodrigo.algamoneyapi.model.Lancamento;
import br.com.rodrigo.algamoneyapi.repository.LancamentoRepsitory;
import br.com.rodrigo.algamoneyapi.repository.filter.LancamentoFilter;
import br.com.rodrigo.algamoneyapi.service.LancamentoService;
import br.com.rodrigo.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    private final LancamentoRepsitory lancamentoRepsitory;

    private final LancamentoService lancamentoService;

    private final ApplicationEventPublisher publisher;

    private final MessageSource messageSource;

    @Autowired
    public LancamentoResource(LancamentoRepsitory lancamentoRepsitory, LancamentoService lancamentoService, ApplicationEventPublisher publisher, MessageSource messageSource) {
        this.lancamentoRepsitory = lancamentoRepsitory;
        this.lancamentoService = lancamentoService;
        this.publisher = publisher;
        this.messageSource = messageSource;
    }

//    @GetMapping
//    public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
//        return   lancamentoRepsitory.filtrar(lancamentoFilter, pageable);
//    }

    @GetMapping
    public List<Lancamento> listar() {
        return lancamentoRepsitory.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> buscarPeloCodigo(@PathVariable Long codigo) {
        Lancamento lancamento = lancamentoRepsitory.findOne(codigo);
        return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }

    @ExceptionHandler(PessoaInexistenteOuInativaException.class)
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex,
                                                                                WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        lancamentoRepsitory.delete(codigo);
    }
}
