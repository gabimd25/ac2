package com.example.ac2_poo.service;

import java.util.List;
import java.util.Optional;

import com.example.ac2_poo.dto.CursoDTO;
import com.example.ac2_poo.model.Curso;
import com.example.ac2_poo.model.Escola;
import com.example.ac2_poo.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CursoService {
    @Autowired
    private CursoRepository repository;
    @Autowired
    private EscolaService escolaService;

    public List<Curso> getAllCursos(){
        return repository.getAllCursos();
    }

    public Curso getCursoByCodigo(int codigo){
        Optional<Curso> op = repository.getCursoByCodigo(codigo);
        return op.orElseThrow( ( )-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Curso nao cadastrado!"));
    }

    public Curso save(Curso curso, int idEscola){
        Escola escola = escolaService.getEscolaByCodigo(idEscola);
        curso.setEscola(escola);
        escola.addCurso(curso);
        return repository.save(curso);
    }

	public Curso update(Curso curso) {
		getCursoByCodigo(curso.getCodigo());
		return repository.update(curso);
	}

	public Curso fromDTO(CursoDTO cursoDTO) {
		Curso curso = new Curso();        
        curso.setTurno(cursoDTO.getTurno());
        return curso;
	}

	public void removeByCodigo(int codigo) {        
        repository.remove(getCursoByCodigo(codigo));
	}
}
