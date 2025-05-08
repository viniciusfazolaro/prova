package br.edu.ifsp.avaliacao.web;

import br.edu.ifsp.avaliacao.model.Candy;
import br.edu.ifsp.avaliacao.model.Order;
import br.edu.ifsp.avaliacao.persistence.CandyRepository;
import br.edu.ifsp.avaliacao.persistence.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("order")
@RequestMapping("/order")
public class OrderController {

    private final CandyRepository candyRepository;
    private final OrderRepository orderRepository;

    public OrderController(CandyRepository candyRepository, OrderRepository orderRepository) {
        this.candyRepository = candyRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{candyId}")
    public String renderPage(@PathVariable Long candyId, Model model) {
        Candy candy = candyRepository.findById(candyId);

        List<String> paymentMethods = List.of("Paypal", "Cartão de crédito", "Cartão de débito", "PIX");

        Order order = new Order();
        order.setCandy(candy);

        model.addAttribute("order", order);
        model.addAttribute("paymentMethods", paymentMethods);

        return "order";
    }


    @PostMapping
    public String processOrder(Order order) {
        orderRepository.save(order);
        order.setCandy(candyRepository.findById(order.getCandy().getId()));
        return "confirm";
    }
}
