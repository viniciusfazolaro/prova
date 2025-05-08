package br.edu.ifsp.avaliacao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candy {

    private Long id;
    private String name;
    private String description;
    private double price;
}
