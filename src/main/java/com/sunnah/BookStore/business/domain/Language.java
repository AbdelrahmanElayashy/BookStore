package com.sunnah.BookStore.business.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum Language {

    DE("DE"), EN("EN");

    private String name;
}
