package br.edu.ifsp.avaliacao.persistence;

import br.edu.ifsp.avaliacao.model.Order;

public interface OrderRepository {
    Order save(Order order);
}
