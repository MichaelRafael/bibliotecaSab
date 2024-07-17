package com.fuctura.bibliotecaSab.dtos;

import com.fuctura.bibliotecaSab.models.Categoria;

public class CategoriaDtoNome {

    private String nome;


    public CategoriaDtoNome() {
    }

    public CategoriaDtoNome(String nome) {

        this.nome = nome;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
