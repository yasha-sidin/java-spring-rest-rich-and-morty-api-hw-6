package ru.overthantutor.JavaSpringRestRickAndMorty.domain;

import lombok.Data;

import java.util.List;

/**
 * Characters
 */
@Data
public class Characters {
    /**
     * info about all characters
     */
    Info info;
    /**
     * All characters
     */
    List<Result> results;
}