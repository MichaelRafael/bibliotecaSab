package com.fuctura.bibliotecaSab.controllers;

import com.fuctura.bibliotecaSab.dtos.CategoriaDto;
import com.fuctura.bibliotecaSab.models.Categoria;
import com.fuctura.bibliotecaSab.services.CategoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<Categoria> findAll() {
        List<Categoria> list = categoriaService.findAll();
        return list;
    }

    @PostMapping
    public Categoria save(@RequestBody Categoria categoria) {
        Categoria cat = categoriaService.save(categoria);
        return cat;
    }

    @PutMapping("/{id}")
    public Categoria update(@PathVariable Integer id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        Categoria cat = categoriaService.update(categoria);
        return cat;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        categoriaService.delete(id);
    }

}
