package ru.overthantutor.JavaSpringRestRickAndMorty.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.overthantutor.JavaSpringRestRickAndMorty.domain.Characters;
import ru.overthantutor.JavaSpringRestRickAndMorty.service.ServiceApi;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rickandmorty/view")
public class ControllerView {
    private final ServiceApi serviceApi;

    @GetMapping
    public String getMain(Model model) {
        Optional<Characters> allCharacters = serviceApi.getAllCharacters(1);
        if (allCharacters.isPresent()) {
            model.addAttribute("pageCurrent", 1);
            model.addAttribute("pagesNum", allCharacters.get().getInfo().getPages());
            model.addAttribute("characters", allCharacters.get().getResults());
            return "index";
        } else {
            return "error";
        }
    }

    @GetMapping("/{page}")
    public String getCharacters(@PathVariable("page") int page, Model model) {
        Optional<Characters> allCharacters = serviceApi.getAllCharacters(page);
        if (allCharacters.isPresent()) {
            model.addAttribute("pageCurrent", page);
            model.addAttribute("pagesNum", allCharacters.get().getInfo().getPages());
            model.addAttribute("characters", allCharacters.get().getResults());
            return "index";
        } else {
            return "error";
        }
    }
}
