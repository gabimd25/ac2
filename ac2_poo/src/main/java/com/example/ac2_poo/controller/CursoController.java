package com.example.ac2_poo.controller;

import java.util.List;

import com.example.ac2_poo.dto.CursoDTO;
import com.example.ac2_poo.model.Curso;
import com.example.ac2_poo.service.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    
    @Autowired
    private CursoService service;

    @GetMapping
    public List<Curso> getAllCursos(){
        return service.getAllCursos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Curso> getCursoByCodigo(@PathVariable int codigo){
        Curso curso = service.getCursoByCodigo(codigo);
        return ResponseEntity.ok(curso);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Curso> update(@RequestBody CursoDTO cursoDTO, @PathVariable int codigo){
        Curso curso = service.fromDTO(cursoDTO);
        curso.setCodigo(codigo);
        curso = service.update(curso);
        return ResponseEntity.ok(curso);
    }
    
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remove(@PathVariable int codigo){
        service.removeByCodigo(codigo);  
        return ResponseEntity.noContent().build();      
    }
}
