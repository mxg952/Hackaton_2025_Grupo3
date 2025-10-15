package com.hackato2025.demo.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hackato2025.demo.model.Newsletter;
import com.hackato2025.demo.repository.NewsletterRepository;

import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

@Service
public class NewsletterService {
    
    @Autowired
    private NewsletterRepository newsletterRepository;
    
    @Autowired
    private JavaMailSender mailSender;
    
    // Guardar suscripción con email de bienvenida
    public boolean suscribirNewsletter(String correo) {
        try {
            System.out.println("📧 Intentando suscribir: " + correo);
            
            if (newsletterRepository.existsByCorreo(correo)) {
                Newsletter existente = newsletterRepository.findByCorreo(correo);
                if (existente.getActivo()) {
                    System.out.println("⚠️ Correo ya está suscrito: " + correo);
                    return false;
                } else {
                    existente.setActivo(true);
                    newsletterRepository.save(existente);
                    System.out.println("✅ Suscripción reactivada: " + correo);
                    
                    new Thread(() -> enviarEmailBienvenida(correo, true)).start();
                    return true;
                }
            }
            
            Newsletter newsletter = new Newsletter(correo);
            Newsletter guardado = newsletterRepository.save(newsletter);
            System.out.println("✅ Nuevo suscriptor guardado con ID: " + guardado.getId());
            
            new Thread(() -> enviarEmailBienvenida(correo, false)).start();
            return true;
            
        } catch (Exception e) {
            System.err.println("❌ Error en suscripción: " + e.getMessage());
            return false;
        }
    }
    
    // Enviar newsletter automáticamente cada 10 minutos
    @Scheduled(fixedRate = 600000)
    public void enviarNewsletterAutomatico() {
        System.out.println("🔄 Ejecutando envío automático de newsletter...");
        
        new Thread(() -> {
            try {
                List<Newsletter> suscriptores = newsletterRepository.findByActivoTrue();
                
                if (suscriptores.isEmpty()) {
                    System.out.println("ℹ️ No hay suscriptores activos");
                    return;
                }
                System.out.println("📧 Enviando newsletter a " + suscriptores.size() + " suscriptores...");
                
                // Generar gráfico de queso
                byte[] chartImage = generarGraficoQueso();
                
                int enviados = 0;
                int errores = 0;
                
                for (Newsletter suscriptor : suscriptores) {
                    try {
                        enviarEmailConGrafico(suscriptor.getCorreo(), chartImage);
                        enviados++;
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.err.println("❌ Error enviando a " + suscriptor.getCorreo() + ": " + e.getMessage());
                        errores++;
                    }
                }
                
                System.out.println("✅ Newsletter enviado: " + enviados + " exitosos, " + errores + " errores");
                
            } catch (Exception e) {
                System.err.println("❌ Error en envío automático: " + e.getMessage());
            }
        }).start();
    }
    
    // Generar gráfico de queso con datos de octubre
    private byte[] generarGraficoQueso() {
        try {
            JSONObject json;
            json = crearJsonDePrueba();
            JSONObject monthlyTourism = json.getJSONObject("monthlyTourism");
            JSONObject absoluteTourists = monthlyTourism.getJSONObject("absoluteTouristsByDistrict");
            
            // Crear dataset para el gráfico de queso
            DefaultPieDataset dataset = new DefaultPieDataset();
            
            // Agregar datos de todos los meses
            dataset.setValue("Enero", absoluteTourists.getInt("january"));
            dataset.setValue("Febrero", absoluteTourists.getInt("february"));
            dataset.setValue("Marzo", absoluteTourists.getInt("march"));
            dataset.setValue("Abril", absoluteTourists.getInt("april"));
            dataset.setValue("Mayo", absoluteTourists.getInt("may"));
            dataset.setValue("Junio", absoluteTourists.getInt("june"));
            dataset.setValue("Julio", absoluteTourists.getInt("july"));
            dataset.setValue("Agosto", absoluteTourists.getInt("august"));
            dataset.setValue("Septiembre", absoluteTourists.getInt("september"));
            dataset.setValue("Octubre", absoluteTourists.getInt("october"));
            dataset.setValue("Noviembre", absoluteTourists.getInt("november"));
            dataset.setValue("Diciembre", absoluteTourists.getInt("december"));
            
            // Crear el gráfico de queso
            JFreeChart chart = ChartFactory.createPieChart(
                "Turismo Mensual 2025",
                dataset,
                true, true, false
            );
            
            chart.setBackgroundPaint(java.awt.Color.WHITE);
            
            // Convertir gráfico a bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(baos, chart, 600, 400);
            
            System.out.println("✅ Gráfico de queso generado correctamente");
            return baos.toByteArray();
            
        } catch (Exception e) {
            System.err.println("❌ Error generando gráfico: " + e.getMessage());
            e.printStackTrace();
            return new byte[0];
        }
    }

    // Método temporal para datos de prueba
    private JSONObject crearJsonDePrueba() {
        JSONObject json = new JSONObject();
        json.put("neighborhoodName", "Barrio de Prueba");
        
        JSONObject monthlyTourism = new JSONObject();
        JSONObject absoluteTourists = new JSONObject();
        
        absoluteTourists.put("january", 120000);
        absoluteTourists.put("february", 135000);
        absoluteTourists.put("march", 170000);
        absoluteTourists.put("april", 210000);
        absoluteTourists.put("may", 250000);
        absoluteTourists.put("june", 280000);
        absoluteTourists.put("july", 320000);
        absoluteTourists.put("august", 350000);
        absoluteTourists.put("september", 270000);
        absoluteTourists.put("october", 220000);
        absoluteTourists.put("november", 150000);
        absoluteTourists.put("december", 180000);
        
        monthlyTourism.put("absoluteTouristsByDistrict", absoluteTourists);
        json.put("monthlyTourism", monthlyTourism);
        
        return json;
    }
    
    // Enviar email con gráfico adjunto
   private void enviarEmailConGrafico(String destinatario, byte[] chartImage) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setTo(destinatario);
            helper.setSubject("Grafico Turismo - " + java.time.LocalDate.now());
            
            // Texto simple sin HTML
            String texto = "Grafico de turismo mensual.\n\n" +
                        "Datos de distribucion de visitantes por meses.\n\n" +
                        "La imagen va adjunta.";
            
            helper.setText(texto);
            
            // Adjuntar el gráfico como archivo adjunto (más simple que inline)
            helper.addAttachment("grafico_turismo.png", 
                                new ByteArrayDataSource(chartImage, "image/png"));
            
            mailSender.send(message);
            System.out.println("✅ Email con gráfico enviado a: " + destinatario);
            
        } catch (Exception e) {
            System.err.println("❌ Error enviando email: " + e.getMessage());
        }
    }
    
    // Enviar email de bienvenida (CORREGIDO)
    private void enviarEmailBienvenida(String destinatario, boolean esReactivacion) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(destinatario);
            
            if (esReactivacion) {
                message.setSubject("🎉 ¡Bienvenido de nuevo! - Newsletter Turístico");
                message.setText("¡Bienvenido de nuevo! Volverás a recibir nuestros gráficos de turismo mensual.");
            } else {
                message.setSubject("🎉 ¡Bienvenido al Newsletter Turístico!");
                message.setText("¡Bienvenido! Recibirás gráficos de distribución de turistas mensuales.");
            }
            
            mailSender.send(message);
            System.out.println("✅ Email de bienvenida enviado a: " + destinatario);
            
        } catch (Exception e) {
            System.err.println("❌ Error enviando email de bienvenida: " + e.getMessage());
        }
    }
}