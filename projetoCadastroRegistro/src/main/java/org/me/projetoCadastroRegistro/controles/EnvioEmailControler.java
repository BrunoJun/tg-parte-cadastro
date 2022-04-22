package org.me.projetoCadastroRegistro.controles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EnvioEmailControler {

    private Logger log = LoggerFactory.getLogger(EnvioEmailControler.class);

    @Autowired
    private JavaMailSender envioEmail;

    @Async
    public void enviar(String destinatario, String texto){

        try {

            MimeMessage mimeMessage = envioEmail.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessageHelper.setText(texto);
            mimeMessageHelper.setTo(destinatario);
            mimeMessageHelper.setSubject("Confirmação do email");
            mimeMessageHelper.setFrom("projetoDadosFatec@gmail.com");
            envioEmail.send(mimeMessage);
        } catch (MessagingException e){

            log.error("Falha ao enviar o email", e);
        }
    }
}
