package org.me.projetoCadastroRegistro.modelos.repositorio;

import org.me.projetoCadastroRegistro.modelos.UsuarioCadastro;
import org.me.projetoCadastroRegistro.modelos.token.ConfirmacaoEmail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ConfirmacaoEmailRepositorio extends PagingAndSortingRepository<ConfirmacaoEmail, Integer> {

    Boolean existsByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmacaoEmail SET confirmadoEmail = true WHERE token = ?1")
    void atualizarEmailConfirmacao(String token);
}
