package com.example.ac2_poo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.ac2_poo.model.Curso;
import com.example.ac2_poo.model.Escola;

import org.springframework.stereotype.Component;

@Component
public class EscolaRepository {
    List<Escola> escolas = new ArrayList<Escola>();
    private int nextCode;
    @PostConstruct
    public void criarEscolas(){
        Escola e1 = new Escola();
        e1.setCodigo(1);
        e1.setNome("ABC");
        e1.setEndereco("Avenida Sao Paulo");
        e1.setTelefone("1532325533");
        e1.setCnpj("112233445566");
        escolas.add(e1);
        nextCode=2;
    }

    public List<Escola> getAllEscolas(){
        return escolas;
    }

    public Optional<Escola> getEscolaByCodigo(int codigo){
        for(Escola aux: escolas){
            if(aux.getCodigo()==codigo){
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    public Escola save(Escola escola){
        escola.setCodigo(nextCode++);
        escolas.add(escola);
        return escola;
    }

    public void remove(Escola escola){
        if(escola.getQuantidadeCursos()==0)
        escolas.remove(escola);
    }

    public Escola update (Escola escola){
        Escola aux = getEscolaByCodigo(escola.getCodigo()).get();
        if(aux!=null){
            aux.setEndereco(escola.getEndereco());
            aux.setTelefone(escola.getTelefone());
        }
        return aux;
    }

	public Optional<ArrayList<Curso>> getCursosEscola(int codigo) {
		for(Escola aux: escolas){
            if(aux.getCodigo()==codigo){
                return Optional.of(aux.getCursos());
            }
        }
        return Optional.empty();
	}
}
