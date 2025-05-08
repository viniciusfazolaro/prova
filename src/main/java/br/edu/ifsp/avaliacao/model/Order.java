package br.edu.ifsp.avaliacao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private String customerName;
    private String address;
    private String paymentMethod;
    private String phoneNumber;
    private Candy candy;
}
