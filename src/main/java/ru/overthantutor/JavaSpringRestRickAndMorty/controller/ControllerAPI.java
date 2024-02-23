package ru.overthantutor.JavaSpringRestRickAndMorty.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.overthantutor.JavaSpringRestRickAndMorty.domain.CharacterInfo;
import ru.overthantutor.JavaSpringRestRickAndMorty.domain.Characters;
import ru.overthantutor.JavaSpringRestRickAndMorty.service.ServiceApi;

import java.util.Optional;

/**
 * Spring Api controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("rickandmorty/api")
public class ControllerAPI {
    private final ServiceApi serviceApi;

    /**
     * Get all characters
     * @param page page num
     * @return     response entity with characters
     */
    @GetMapping("/{page}")
    public ResponseEntity<Characters> getCharacters(@PathVariable("page") int page) {
        Optional<Characters> allCharacters = serviceApi.getAllCharacters(page);
        return allCharacters.map(characters -> new ResponseEntity<>(characters, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_GATEWAY));
    }

    /**
     * Get character by id
     * @param id character id
     * @return   response entity with character info
     */
    @GetMapping("/character/{id}")
    public ResponseEntity<CharacterInfo> getCharacterById(@PathVariable("id") long id) {
        CharacterInfo characterInfo = serviceApi.getCharacterById(id);
        return new ResponseEntity<>(characterInfo, HttpStatus.OK);
    }
}