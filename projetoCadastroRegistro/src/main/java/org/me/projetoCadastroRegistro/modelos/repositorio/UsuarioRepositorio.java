package org.me.projetoCadastroRegistro.modelos.repositorio;

import org.me.projetoCadastroRegistro.modelos.UsuarioCadastro;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepositorio extends PagingAndSortingRepository<UsuarioCadastro, Integer> {
}
