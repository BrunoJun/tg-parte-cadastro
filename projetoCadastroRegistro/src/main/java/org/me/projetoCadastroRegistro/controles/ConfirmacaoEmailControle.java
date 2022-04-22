package org.me.projetoCadastroRegistro.controles;

import org.me.projetoCadastroRegistro.modelos.repositorio.ConfirmacaoEmailRepositorio;
import org.me.projetoCadastroRegistro.modelos.token.ConfirmacaoEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfirmacaoEmailControle {

    @Autowired
    private ConfirmacaoEmailRepositorio confirmacaoEmailRepositorio;

    @Autowired
    private EnvioEmailControler envioEmailControler;

    public ConfirmacaoEmailControle() {
    }

    @PutMapping("/cadastro/confirmar")
    public String confirmarToken(@RequestParam String token){

        Boolean confirmacaoEmail = confirmacaoEmailRepositorio.existsByToken(token);

        if(confirmacaoEmail){

            confirmacaoEmailRepositorio.atualizarEmailConfirmacao(token);
            return "Email confirmado!";
        }

        return "Email não confirmado";
    }

    public void salvarEmail(ConfirmacaoEmail confirmacaoEmail){

        confirmacaoEmailRepositorio.save(confirmacaoEmail);
    }

    public void enviarEmail(String email, String token){

        String link = "<a href='http://localhost:8080/cadastro/" +
                "confirmar?token=" + token + "'></a>";

        envioEmailControler.enviar(email, link);
    }
}
