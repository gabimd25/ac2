package com.example.ac2_poo.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Escola {
    
    private int codigo;
    private String nome;
    private String endereco;
    private String telefone;
    private String cnpj;
    @JsonIgnore
    private ArrayList<Curso> cursos = new ArrayList<Curso>();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public boolean removeCurso(Curso curso){
        return cursos.remove(curso);
    }

    public boolean addCurso(Curso curso){
        return cursos.add(curso);
    }

    public int getQuantidadeCursos(){
        return cursos.size();
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }
}
