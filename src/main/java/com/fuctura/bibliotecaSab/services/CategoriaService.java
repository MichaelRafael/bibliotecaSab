package com.fuctura.bibliotecaSab.services;

import com.fuctura.bibliotecaSab.dtos.CategoriaDto;
import com.fuctura.bibliotecaSab.exceptions.DataIntegrityViolationException;
import com.fuctura.bibliotecaSab.exceptions.IllegalArgumentException;
import com.fuctura.bibliotecaSab.exceptions.ObjectNotFoundException;
import com.fuctura.bibliotecaSab.models.Categoria;
import com.fuctura.bibliotecaSab.repositories.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoriaRepository categoriaRepository;
    public Categoria findById(Integer id) {
        Optional<Categoria> cat = categoriaRepository.findById(id);
        if (cat.isPresent()) {
            return cat.get();
        }
        throw new ObjectNotFoundException("Categoria não encontrada!");
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria save(CategoriaDto categoriaDto) {
        findByNome(categoriaDto);
        return categoriaRepository.save(modelMapper.map(categoriaDto, Categoria.class));
    }

    public Categoria update(CategoriaDto categoriaDto) {
        findById(categoriaDto.getId());
        return save(categoriaDto);
//        findByNome(categoriaDto);
//        return categoriaRepository.save(modelMapper.map(categoriaDto, Categoria.class));
    }

    public void delete(Integer id) {
        findById(id);
        Optional<Categoria> cat = categoriaRepository.findById(id);
        if (!cat.get().getLivros().isEmpty()) {
            throw new DataIntegrityViolationException("Categoria não pode ser deletada, pois, possui livros!!!");
        }
        categoriaRepository.deleteById(id);
    }

    private void findByNome(CategoriaDto categoriaDto) {
        Optional<Categoria> cat = categoriaRepository.findByNome(categoriaDto.getNome());
        if (cat.isPresent() && cat.get().getNome().equals(categoriaDto.getNome())) {
            throw new IllegalArgumentException("Já existe uma categoria com este nome " + categoriaDto.getNome());
        }
    }

    public void buscarPorNome(String nome) {
        Optional<Categoria> cat = categoriaRepository.findByNomeContainingIgnoreCase(nome);
        if (cat.isEmpty()) {
            throw new ObjectNotFoundException("Categoria não existe!");
        }
    }
}

