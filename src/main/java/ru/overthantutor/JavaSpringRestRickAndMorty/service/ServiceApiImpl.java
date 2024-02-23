package ru.overthantutor.JavaSpringRestRickAndMorty.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.overthantutor.JavaSpringRestRickAndMorty.domain.CharacterInfo;
import ru.overthantutor.JavaSpringRestRickAndMorty.domain.Characters;
import ru.overthantutor.JavaSpringRestRickAndMorty.domain.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;

@Service
@RequiredArgsConstructor
@Log
public class ServiceApiImpl implements ServiceApi {
    private final RestTemplate template;
    private final HttpHeaders headers;

    private static final String CHARACTER_API = "https://rickandmortyapi.com/api/character";

    @Override
    public Optional<Characters> getAllCharacters(int page) {
        Optional<Characters> optional = Optional.empty();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Characters> response = template.exchange(CHARACTER_API + "?page=" + page, HttpMethod.GET, entity, Characters.class);
            int poolSize = response.getBody().getResults().size();
            ExecutorService service = Executors.newFixedThreadPool(poolSize);
            List<Future<Runnable>> futures = new ArrayList<>();
            for (Result result : response.getBody().getResults()) {
                Future<Runnable> f = (Future<Runnable>) service.submit(() -> result.setCharacterInfo(getCharacterById(result.getId())));
                futures.add(f);
            }
            for (Future<Runnable> future : futures) {
                future.get();
            }
            service.shutdownNow();
            optional = Optional.of(response.getBody());
        } catch (HttpClientErrorException | InterruptedException | ExecutionException e) {
            log.log(Level.INFO, "Wrong response! Error: " + e.getMessage());
        }
        return optional;
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