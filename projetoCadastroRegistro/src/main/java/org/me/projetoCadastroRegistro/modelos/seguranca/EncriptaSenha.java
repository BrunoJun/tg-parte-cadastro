package org.me.projetoCadastroRegistro.modelos.seguranca;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncriptaSenha {

    public String assegurarSenha(String senha){

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        senha = passwordEncoder.encode(senha);
        return senha;
    }
}
