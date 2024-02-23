package ru.overthantutor.JavaSpringRestRickAndMorty.service;

import ru.overthantutor.JavaSpringRestRickAndMorty.domain.CharacterInfo;
import ru.overthantutor.JavaSpringRestRickAndMorty.domain.Characters;

import java.util.Optional;

/**
 * Service API
 */
public interface ServiceApi {
    /**
     * Get all characters
     *
     * @param page page num
     * @return all characters in this page
     */
    Optional<Characters> getAllCharacters(int page);

    /**
     * Get character by id
     * @param id character id
     * @return   info about character
     */
    CharacterInfo getCharacterById(long id);
}
