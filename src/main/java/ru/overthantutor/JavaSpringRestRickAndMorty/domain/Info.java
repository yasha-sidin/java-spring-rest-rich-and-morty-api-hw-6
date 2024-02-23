package ru.overthantutor.JavaSpringRestRickAndMorty.domain;

import lombok.Data;

/**
 * Info about all characters
 */
@Data
public class Info {
    private Integer count;
    private Integer pages;
    private String next;
    private String prev;
}