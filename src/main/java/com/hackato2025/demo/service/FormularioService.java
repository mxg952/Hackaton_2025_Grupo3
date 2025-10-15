package com.hackato2025.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackato2025.demo.model.Formulario;
import com.hackato2025.demo.repository.FormularioRepository;

@Service
public class FormularioService {
    
    @Autowired
    private FormularioRepository formularioRepository;
    
    // CORREGIDO: Ahora devuelve boolean en lugar de Formulario
    public boolean guardarFormulario(boolean residenteLocal, String barrio, int... notas) {
        try {
            validarNotas(notas);
            double notaMedia = calcularMedia(notas);
            
            Formulario formulario = new Formulario(residenteLocal, barrio, notaMedia);
            formularioRepository.save(formulario);
            return true; // Devuelve true si se guarda correctamente
            
        } catch (Exception e) {
            return false; // Devuelve false si hay error
        }
    }
    
    // CORREGIDO: Método específico para calcular media general
    public Double obtenerMediaGeneralPorBarrio(String barrio) {
        List<Formulario> formulariosDelBarrio = formularioRepository.findByBarrio(barrio);
        
        if (formulariosDelBarrio.isEmpty()) {
            return 0.0;
        }
        
        double sumaNotasMedias = 0;
        for (Formulario formulario : formulariosDelBarrio) {
            sumaNotasMedias += formulario.getNotaMedia();
        }
        
        double mediaGeneral = sumaNotasMedias / formulariosDelBarrio.size();
        return Math.round(mediaGeneral * 100.0) / 100.0;
    }
    
    // Método auxiliar para obtener formularios por barrio (si lo necesitas)
    public List<Formulario> obtenerFormulariosPorBarrio(String barrio) {
        return formularioRepository.findByBarrio(barrio);
    }
    
    private double calcularMedia(int[] notas) {
        double suma = 0;
        for (int nota : notas) {
            suma += nota;
        }
        return Math.round((suma / notas.length) * 100.0) / 100.0;
    }
    
    private void validarNotas(int[] notas) {
        if (notas.length == 0) {
            throw new IllegalArgumentException("Debe proporcionar al menos una nota");
        }
        
        for (int nota : notas) {
            if (nota < 1 || nota > 5) {
                throw new IllegalArgumentException("Las notas deben estar entre 1 y 5");
            }
        }
    }
}
