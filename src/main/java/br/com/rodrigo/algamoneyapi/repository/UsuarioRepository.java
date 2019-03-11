package br.com.rodrigo.algamoneyapi.repository;

import br.com.rodrigo.algamoneyapi.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
