package ru.overthantutor.JavaSpringRestRickAndMorty.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.overthantutor.JavaSpringRestRickAndMorty.domain.Characters;
import ru.overthantutor.JavaSpringRestRickAndMorty.service.ServiceApi;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rickandmorty/view")
public class ControllerView {
    private final ServiceApi serviceApi;

    @GetMapping
    public String getMain(Model model) {
        Characters allCharacters = serviceApi.getAllCharacters(1);
        model.addAttribute("pageCurrent", 1);
        model.addAttribute("pagesNum", allCharacters.getInfo().getPages());
        model.addAttribute("characters", allCharacters.getResults());
        return "index";
    }

    @GetMapping("/{page}")
    public String getCharacters(@PathVariable("page") int page, Model model) {
        Characters allCharacters = serviceApi.getAllCharacters(page);
        model.addAttribute("pageCurrent", page);
        model.addAttribute("pagesNum", allCharacters.getInfo().getPages());
        model.addAttribute("characters", allCharacters.getResults());
        return "index";
    }
}
