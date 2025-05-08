package br.edu.ifsp.avaliacao.web;

import br.edu.ifsp.avaliacao.model.Candy;
import br.edu.ifsp.avaliacao.model.Order;
import br.edu.ifsp.avaliacao.persistence.CandyRepository;
import br.edu.ifsp.avaliacao.persistence.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public String renderPage(Model model) {
        List<String> paymentMethods = new ArrayList<>();
        List<Candy> candies = new ArrayList<>();
        candyRepository.findAll().forEach(candies::add);
        paymentMethods.add("Paypal");
        paymentMethods.add("Cartão de crédito");
        paymentMethods.add("Cartão de débito");
        paymentMethods.add("PIX");

        model.addAttribute("order", new Order());
        model.addAttribute("paymentMethods", paymentMethods);
        model.addAttribute("candies", candies);

        return "home";
    }

    @PostMapping
    public String processOrder(Order order) {
        orderRepository.save(order);
        order.setCandy(candyRepository.findById(order.getCandy().getId()));
        return "confirmacao";
    }
}
