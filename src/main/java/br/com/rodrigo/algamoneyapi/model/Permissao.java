package br.com.rodrigo.algamoneyapi.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "permissao")
public class Permissao {

    private Long codigo;

    private String descricao;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Permissao)) return false;
        Permissao permissao = (Permissao) o;
        return Objects.equals(codigo, permissao.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
