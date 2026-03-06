package com.consecionario.autos.controller;

import com.consecionario.autos.model.Auto;
import com.consecionario.autos.repository.AutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/autos")
public class AutoController {

    private final AutoRepository autoRepository;

    public AutoController(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Auto>> listarAutos() {
        List<Auto> autos = autoRepository.findAll();
        return ResponseEntity.ok(autos);
    }

    @PostMapping
    public ResponseEntity<String> insertarAuto(@RequestBody Auto auto) {
        int filasAfectadas = autoRepository.save(auto);
        if (filasAfectadas > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Auto registrado con exito");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el auto");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAuto(@PathVariable Integer id) {
        int filasAfectadas = autoRepository.deleteById(id);
        if (filasAfectadas > 0) {
            return ResponseEntity.ok("Auto con id " + id + " eliminado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro un auto con id " + id);
        }
    }
}