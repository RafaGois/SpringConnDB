package io.github.RafaGois.rest.controller;

import io.github.RafaGois.domain.entity.Produto;
import io.github.RafaGois.domain.repository.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private Produtos repository;

    public ProdutoController(Produtos repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    private Produto save ( @RequestBody @Valid Produto produto ) {
        return repository.save(produto);
    }

    @PutMapping("{id}")
    @ResponseStatus(CREATED)
    public void update (@PathVariable @Valid Integer id, @RequestBody @Valid Produto produto) {
        repository
                .findById(id)
                .map( p -> {
                    produto.setId(p.getId());
                    repository.save(produto);
                    return p;
                }).orElseThrow( () -> new ResponseStatusException(NOT_FOUND,"Produto não encontrado."));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable Integer id) {
        repository.findById(id)
                .map( p -> {
                    repository.delete(p);
                    return p;
                }).orElseThrow( () -> new ResponseStatusException(NOT_FOUND,"Produto não encontrado."));
    }

    @GetMapping("{id}")
    public Produto findById (@PathVariable Integer id) {
       return repository
               .findById(id)
               .orElseThrow(
                       () -> new ResponseStatusException(NOT_FOUND,"Produto não encontrado."));
    }

    @GetMapping
    public List<Produto> find (Produto filtro) {

        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro,exampleMatcher);
        return repository.findAll(example);
    }

}
