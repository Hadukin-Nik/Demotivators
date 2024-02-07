package com.demotivators.site.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
public class Meme {
    private final String name;
    private final String image;

    private Long id;

    private Date creationDate;
}
