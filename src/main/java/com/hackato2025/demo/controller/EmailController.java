package com.hackato2025.demo.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.internet.MimeMessage;

@RestController
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    // 1️⃣ Endpoint: enviar alerta de densidad alta
    @PostMapping("/alerta")
    public String enviarAlerta(@RequestParam String destino, @RequestParam double densidad) {
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo(destino);
            mensaje.setSubject("⚠️ Alerta de densidad alta");
            mensaje.setText("La densidad ha subido a " + densidad + ". Revisa el sistema.");
            mailSender.send(mensaje);
            return "Correo de alerta enviado a " + destino;
        } catch (Exception e) {
            return "Error al enviar alerta: " + e.getMessage();
        }
    }

    // 2️⃣ Endpoint: enviar correo con gráfico adjunto
    @PostMapping("/grafico")
    public String enviarGrafico(@RequestParam String destino) {
        try {
            // Supongamos que ya tienes un gráfico generado en "grafico.png"
            File archivo = new File("grafico.png");
            if (!archivo.exists()) return "No se encontró el archivo gráfico.";

            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
            helper.setTo(destino);
            helper.setSubject("📊 Reporte de densidad");
            helper.setText("Adjunto encontrarás el gráfico más reciente del monitoreo.");

            FileSystemResource adjunto = new FileSystemResource(archivo);
            helper.addAttachment("grafico.png", adjunto);

            mailSender.send(mensaje);
            return "Correo con gráfico enviado a " + destino;
        } catch (Exception e) {
            return "Error al enviar gráfico: " + e.getMessage();
        }
    }
}
