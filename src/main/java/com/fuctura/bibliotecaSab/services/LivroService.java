package com.fuctura.bibliotecaSab.services;

import com.fuctura.bibliotecaSab.dtos.LivroDto;
import com.fuctura.bibliotecaSab.exceptions.ObjectNotFoundException;
import com.fuctura.bibliotecaSab.models.Categoria;
import com.fuctura.bibliotecaSab.models.Livro;
import com.fuctura.bibliotecaSab.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Livro findById(Integer id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            return livro.get();
        }
        throw new ObjectNotFoundException("Livro não encontrado!!!");
    }

    public List<Livro> findAll(Integer id_cat) {
        categoriaService.findById(id_cat);
        return livroRepository.findAllLivrosByCategoria(id_cat);
    }

    public List<Livro> findAllLivrosByCategoria(String nome) {
        categoriaService.buscarPorNome(nome);
        return livroRepository.findByCategoriaNomeContainingIgnoreCase(nome);
    }

    public Livro save(Integer id, LivroDto livroDto) {
        livroDto.setId(null);
        Categoria cat = categoriaService.findById(id);
        livroDto.setCategoria(cat);
        return livroRepository.save(new Livro(livroDto));
    }
}
