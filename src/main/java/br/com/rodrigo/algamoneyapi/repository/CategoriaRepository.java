package br.com.rodrigo.algamoneyapi.repository;

import br.com.rodrigo.algamoneyapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
