package com.hackato2025.demo.controller;

import com.hackato2025.demo.model.Newsletter;
import com.hackato2025.demo.service.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/newsletter")
@CrossOrigin(origins = "*")
public class NewsletterController {
    
    @Autowired
    private NewsletterService newsletterService;
    
    @PostMapping("/suscribir")
    public ResponseEntity<Map<String, Object>> suscribirNewsletter(@RequestParam String correo) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            boolean suscrito = newsletterService.suscribirNewsletter(correo);
            
            if (suscrito) {
                response.put("success", true);
                response.put("message", "Suscripción realizada correctamente");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "El correo ya está suscrito");
                return ResponseEntity.badRequest().body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error en la suscripción: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
}