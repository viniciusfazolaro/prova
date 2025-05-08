package br.edu.ifsp.avaliacao.web;

import br.edu.ifsp.avaliacao.persistence.CandyRepository;
import br.edu.ifsp.avaliacao.persistence.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/candy")
public class CandyController {

    private final CandyRepository candyRepository;

    public  CandyController(CandyRepository candyRepository) {
        this.candyRepository = candyRepository;
    }

    @GetMapping("/list")
    public String list(Model model) {

        model.addAttribute("candies", candyRepository.findAll());
        return "list";
    }

}
