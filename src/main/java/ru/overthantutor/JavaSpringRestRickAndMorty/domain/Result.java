package ru.overthantutor.JavaSpringRestRickAndMorty.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * Character body with character info
 */
@Data
public class Result {
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private String image;
    private String url;
    private Date created;
    @JsonIgnore
    private CharacterInfo characterInfo;
}
