package com.fuctura.bibliotecaSab.dtos;

import com.fuctura.bibliotecaSab.enuns.Tamanho;
import com.fuctura.bibliotecaSab.models.Categoria;
import com.fuctura.bibliotecaSab.models.Livro;

public class LivroDto {

    private Integer id;
    private String nome;
    private String autor;
    private String texto;
    private Tamanho tamanho;
    private Categoria categoria;

    public LivroDto() {
    }

    public LivroDto(Integer id, String nome, String autor, String texto, Tamanho tamanho, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.texto = texto;
        this.tamanho = tamanho;
        this.categoria = categoria;
    }

    public LivroDto(Livro livro) {
        this.id = livro.getId();
        this.nome = livro.getNome();
        this.autor = livro.getAutor();
        this.texto = livro.getTexto();
        this.tamanho = livro.getTamanho();
        this.categoria = livro.getCategoria();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
