package com.example.ac2_poo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.ac2_poo.dto.EscolaDTO;
import com.example.ac2_poo.model.Curso;
import com.example.ac2_poo.model.Escola;
import com.example.ac2_poo.repository.EscolaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EscolaService {
    @Autowired
    private EscolaRepository repository;

    public Escola fromDTO(EscolaDTO dto){
        Escola escola = new Escola();
        escola.setNome(dto.getNome());
        escola.setEndereco(dto.getEndereco());
        escola.setTelefone(dto.getTelefone());
        return escola;
    }

	public List<Escola> getAllEscolas() {
		return repository.getAllEscolas();
    }
    
    public Escola getEscolaByCodigo(int codigo) {
        Optional<Escola> op = repository.getEscolaByCodigo(codigo);
        return op.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Escola nao cadastrada!"));
    }
    
	public void removeByCodigo(int codigo) {
        repository.remove(getEscolaByCodigo(codigo));
    }
    
	public Escola update(Escola escola) {
        getEscolaByCodigo(escola.getCodigo());
		return repository.update(escola);
	}
	
	public Escola save(Escola escola) {
		return repository.save(escola);
	}

	public ArrayList<Curso> getCursosEscola(int codigo) {
		Optional<ArrayList<Curso>> op = repository.getCursosEscola(codigo);
        return op.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum curso cadastrado!"));
	}
}
