package com.example.ac2_poo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.ac2_poo.dto.EscolaDTO;
import com.example.ac2_poo.model.Curso;
import com.example.ac2_poo.model.Escola;
import com.example.ac2_poo.service.CursoService;
import com.example.ac2_poo.service.EscolaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/escolas")
public class EscolaController {
    @Autowired
    private EscolaService escolaService;
    @Autowired
    private CursoService cursoService;
    @GetMapping()
    public List<Escola> getEscolas() {
        return escolaService.getAllEscolas();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Escola> getEscolaByCodigo(@PathVariable int codigo){
        Escola escola = escolaService.getEscolaByCodigo(codigo);
        return ResponseEntity.ok(escola);
    }

    @GetMapping("/{codigo}/cursos")
    public ResponseEntity<ArrayList<Curso>> getCursosEscola(@PathVariable int codigo){
        ArrayList<Curso> cursos = escolaService.getCursosEscola(codigo);
        return ResponseEntity.ok(cursos);
    }

    @PostMapping()
    public ResponseEntity<Escola> save(@RequestBody EscolaDTO escolaDTO, HttpServletRequest request, UriComponentsBuilder builder){
        Escola escola = escolaService.fromDTO(escolaDTO);
        Escola novaEscola = escolaService.save(escola);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + novaEscola.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remove(@PathVariable int codigo){
        escolaService.removeByCodigo(codigo);  
        return ResponseEntity.noContent().build();      
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Escola> update(@RequestBody EscolaDTO escolaDTO, @PathVariable int codigo){
        Escola escola = escolaService.fromDTO(escolaDTO);
        escola.setCodigo(codigo);
        escola = escolaService.update(escola);
        return ResponseEntity.ok(escola);
    }

    @PostMapping("{idEscola}/cursos")
    public ResponseEntity<Curso> save(@PathVariable int idEscola, @RequestBody Curso curso, HttpServletRequest request, UriComponentsBuilder builder){
        curso = cursoService.save(curso, idEscola);
        UriComponents uriComponents = builder.path(request.getRequestURI()+"/"+curso.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }
    
}
