package com.hackato2025.demo.controller;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackato2025.demo.service.FormularioService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FormularioController {

    @Autowired
    private FormularioService formularioService;

    // ENDPOINT MODIFICADO para recibir JSON
    @PostMapping("/formularios")
    public ResponseEntity<Map<String, Object>> guardarDatosFormularios(
            @RequestBody Map<String, Object> formularioData) {

        Map<String, Object> response = new HashMap<>();

        try {
            // Extraer los valores del JSON
            boolean resident = Boolean.valueOf(formularioData.get("resident").toString());
            String neiborhood = formularioData.get("neiborhood").toString();
            int cleaning = Integer.parseInt(formularioData.get("cleaning").toString());
            int accessibility = Integer.parseInt(formularioData.get("accessibility").toString());
            int peopleCuantity = Integer.parseInt(formularioData.get("peopleCuantity").toString());
            int comercialActivity = Integer.parseInt(formularioData.get("comercialActivity").toString());
            int security = Integer.parseInt(formularioData.get("security").toString());
            int noise = Integer.parseInt(formularioData.get("noise").toString());
            int lighting = Integer.parseInt(formularioData.get("lighting").toString());

            boolean guardadoExitoso = formularioService.guardarFormulario(
                resident, 
                neiborhood, 
                cleaning,   
                accessibility, 
                peopleCuantity, 
                comercialActivity, 
                security,   
                noise,      
                lighting    
            );

            if (guardadoExitoso) {
                response.put("success", true);
                response.put("message", "Formulario guardado correctamente");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Error al guardar el formulario");
                return ResponseEntity.badRequest().body(response);
            }

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error interno del servidor: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    // ENDPOINT GET para obtener media por barrio
    @GetMapping("/formularios/media/{barrio}")
    public ResponseEntity<Map<String, Object>> obtenerMediaGeneralPorBarrio(@PathVariable String barrio) {
        Map<String, Object> response = new HashMap<>();

        try {
            Double mediaGeneral = formularioService.obtenerMediaGeneralPorBarrio(barrio);

            response.put("success", true);
            response.put("barrio", barrio);
            response.put("mediaGeneral", mediaGeneral);
            response.put("message", "Media general calculada correctamente");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al calcular la media general: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    @GetMapping("/barrios")
    public List<String> obtenerNomBarriDesdeArchivo() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getClassLoader()
            .getResourceAsStream("datosturismo.json"); 
        if (is == null) throw new FileNotFoundException("datosturismo.json no encontrado en resources");

        JsonNode root = mapper.readTree(is);

        List<String> nombres = new ArrayList<>();
        if (root.isArray()) {
            for (JsonNode element : root) {
                JsonNode nombreNode = element.get("nom_barri");
                if (nombreNode != null && !nombreNode.isNull()) {
                    nombres.add(nombreNode.asText());
                }
            }
        }
        return nombres;
    }
}