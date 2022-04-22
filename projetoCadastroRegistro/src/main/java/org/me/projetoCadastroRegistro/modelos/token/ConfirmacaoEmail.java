package org.me.projetoCadastroRegistro.modelos.token;

import org.apache.tomcat.jni.Local;
import org.me.projetoCadastroRegistro.modelos.UsuarioCadastro;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ConfirmacaoEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime criadoData;

    @Column(nullable = false)
    private Boolean confirmadoEmail;

    @ManyToOne
    @JoinColumn(nullable = false, name = "usuario_id")
    private UsuarioCadastro usuarioCadastro;

    public ConfirmacaoEmail(String token, LocalDateTime criadoData,
                            Boolean confirmadoEmail, UsuarioCadastro usuarioCadastro) {

        this.token = token;
        this.criadoData = criadoData;
        this.confirmadoEmail = confirmadoEmail;
        this.usuarioCadastro = usuarioCadastro;
    }

    public ConfirmacaoEmail() {}
}
