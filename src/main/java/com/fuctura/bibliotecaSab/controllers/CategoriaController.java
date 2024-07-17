package com.fuctura.bibliotecaSab.controllers;

import com.fuctura.bibliotecaSab.dtos.CategoriaDto;
import com.fuctura.bibliotecaSab.dtos.CategoriaDtoNome;
import com.fuctura.bibliotecaSab.models.Categoria;
import com.fuctura.bibliotecaSab.services.CategoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    //@GetMapping("/{id}") = busca por id
    //@GetMapping = buscar todos obj
    //@PostMapping(obj) = salvar no banco
    //@PutMapping(obj) = atualizar obj
    //@DeleteMapping("/{id}) = deletar obj

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoriaDto> findById(@PathVariable Integer id) {
        Categoria cat = categoriaService.findById(id);
        CategoriaDto catDto = modelMapper.map(cat, CategoriaDto.class);

        return ResponseEntity.ok().body(catDto);
    }

    @GetMapping(value = "/nome")
    public ResponseEntity<List<CategoriaDtoNome>> findAllByNome() {
        List<Categoria> list = categoriaService.findAll();
        return ResponseEntity.ok().body(list.stream().map(obj -> modelMapper.map(obj, CategoriaDtoNome.class)).collect(Collectors.toList()));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAll() {
        List<Categoria> list = categoriaService.findAll();
        return ResponseEntity.ok().body(list.stream().map(obj -> modelMapper.map(obj, CategoriaDto.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> save(@RequestBody CategoriaDto categoriaDto) {
        Categoria cat = categoriaService.save(categoriaDto);
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update(@PathVariable Integer id, @RequestBody CategoriaDto categoriaDto) {
        categoriaDto.setId(id);
        Categoria cat = categoriaService.update(categoriaDto);
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
