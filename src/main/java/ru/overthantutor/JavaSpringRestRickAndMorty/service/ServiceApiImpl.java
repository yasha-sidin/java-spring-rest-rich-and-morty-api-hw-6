package ru.overthantutor.JavaSpringRestRickAndMorty.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.overthantutor.JavaSpringRestRickAndMorty.domain.CharacterInfo;
import ru.overthantutor.JavaSpringRestRickAndMorty.domain.Characters;
import ru.overthantutor.JavaSpringRestRickAndMorty.domain.Result;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceApiImpl implements ServiceApi{
    private final RestTemplate template;
    private final HttpHeaders headers;

    private  static final String CHARACTER_API = "https://rickandmortyapi.com/api/character";
    @Override
    public Characters getAllCharacters(int page) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Characters> response = template.exchange(CHARACTER_API + "?page=" + page, HttpMethod.GET, entity, Characters.class);
            for (Result result : response.getBody().getResults()) {
                result.setCharacterInfo(getCharacterById(result.getId()));
            }
            return response.getBody();
        } catch (HttpClientErrorException e) {
            return new Characters();
        }
    }

    @Override
    public CharacterInfo getCharacterById(long id) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<CharacterInfo> response = template.exchange(CHARACTER_API + "/" + id, HttpMethod.GET, entity, CharacterInfo.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            return new CharacterInfo();
        }
    }
}