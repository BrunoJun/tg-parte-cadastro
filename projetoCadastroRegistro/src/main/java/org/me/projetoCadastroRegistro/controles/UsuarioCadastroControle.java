package org.me.projetoCadastroRegistro.controles;

import org.me.projetoCadastroRegistro.modelos.UsuarioCadastro;
import org.me.projetoCadastroRegistro.modelos.seguranca.EncriptaSenha;
import org.me.projetoCadastroRegistro.modelos.token.ConfirmacaoEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.me.projetoCadastroRegistro.modelos.repositorio.UsuarioRepositorio;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class UsuarioCadastroControle {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ConfirmacaoEmailControle confirmacaoEmailControle;

    private EncriptaSenha encriptaSenha = new EncriptaSenha();

    @PostMapping("/cadastro")
    public UsuarioCadastro novoUsuario(@RequestParam String nome,
                                       @RequestParam String email,
                                       @RequestParam String senha) {

        UsuarioCadastro usuarioCadastro = new UsuarioCadastro(nome, email, encriptaSenha.assegurarSenha(senha));
        usuarioRepositorio.save(usuarioCadastro);

        // Criação e envio do email de confirmação
        criarToken(usuarioCadastro, email);

        return usuarioCadastro;
    }

    @GetMapping("/mensagem")
    public String ola(){

        return "ola";
    }

    private void criarToken(UsuarioCadastro usuarioCadastro, String email){

        String token = UUID.randomUUID().toString();
        ConfirmacaoEmail confirmacaoEmail = new ConfirmacaoEmail(token, LocalDateTime.now(),
                false, usuarioCadastro);
        confirmacaoEmailControle.salvarEmail(confirmacaoEmail);
        confirmacaoEmailControle.enviarEmail(email, token);
    }
}
