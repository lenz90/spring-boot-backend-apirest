package com.bolsadeideas.springboot.backend.apirest.util;

import io.reactivex.Flowable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class Page<T> {
    private List<T> content;
    private Boolean first;
    private Boolean last;
    private int totalPages;
    private int totalElements;
    private int size;
    private int number;
    private int numbersOfElements;
}
