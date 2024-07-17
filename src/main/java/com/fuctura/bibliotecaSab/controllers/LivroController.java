package com.fuctura.bibliotecaSab.controllers;

import com.fuctura.bibliotecaSab.dtos.CategoriaDto;
import com.fuctura.bibliotecaSab.dtos.LivroDto;
import com.fuctura.bibliotecaSab.models.Livro;
import com.fuctura.bibliotecaSab.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<LivroDto> findById(@PathVariable Integer id) {
        Livro livro = livroService.findById(id);
        return ResponseEntity.ok().body(new LivroDto(livro));
    }

    @GetMapping
    public ResponseEntity<List<LivroDto>> findAll(@RequestParam(value = "categoria", defaultValue = "0") Integer id) {
        List<Livro> list = livroService.findAll(id);
        return ResponseEntity.ok().body(list.stream().map(obj -> new LivroDto(obj)).collect(Collectors.toList()));
        //localhhost:8080/livro?categoria=1
    }

    @GetMapping("/categoria/{nome}")
    public ResponseEntity<List<LivroDto>> findAllLivrosByNome(@PathVariable String nome) {
        List<Livro> list = livroService.findAllLivrosByCategoria(nome);
        return ResponseEntity.ok().body(list.stream().map(obj -> new LivroDto(obj)).collect(Collectors.toList()));

    }

    @PostMapping
    public ResponseEntity<LivroDto> save(@RequestParam(value = "categoria", defaultValue = "0") Integer id,
                                         @RequestBody LivroDto livroDto) {
        Livro livro = livroService.save(id, livroDto);
        return ResponseEntity.ok().body(new LivroDto(livro));
    }

}
