package com.example.ac2_poo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.ac2_poo.model.Curso;
import com.example.ac2_poo.model.Escola;

import org.springframework.stereotype.Component;
@Component
public class CursoRepository {
    private ArrayList<Curso> cursos = new ArrayList<Curso>();
    private static int nextCodigo=1;

    public List<Curso> getAllCursos(){
        return cursos;
    }

    public Optional<Curso> getCursoByCodigo(int codigo){
        for(Curso aux : cursos){
            if(aux.getCodigo() == codigo){
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    public Curso save(Curso curso){
        curso.setCodigo(nextCodigo);
        cursos.add(curso);
        return curso;
    }
    public Curso update (Curso curso){
        Curso aux = getCursoByCodigo(curso.getCodigo()).get();
        if(aux!=null){
            aux.setTurno(curso.getTurno());
        }
        return aux;
    }

	public void remove(Curso curso) {
        Escola escola = curso.getEscola();
        escola.removeCurso(curso);
        cursos.remove(curso);
	}
}
