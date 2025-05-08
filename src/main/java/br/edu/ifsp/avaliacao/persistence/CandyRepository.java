package br.edu.ifsp.avaliacao.persistence;

import br.edu.ifsp.avaliacao.model.Candy;

public interface CandyRepository {
    Iterable<Candy> findAll();
    Candy findById(Long id);
}
