package com.sunnah.BookStore.data.Entity;

import com.sunnah.BookStore.business.domain.Categorie;
import com.sunnah.BookStore.business.domain.Language;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="BOOK")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @NotNull
    private String name;

    @Column(length = 300)
    private String description;

    @Column(length = 255)
    @NotNull
    private String imagePath;

    @NotNull
    private String pdfPath;

    @Enumerated(EnumType.STRING)
    private Categorie category;

    @Enumerated(EnumType.STRING)
    private Language language;

    @OneToMany(mappedBy = "book")
    private List<BasketItem> basketItems = new ArrayList<>();


    public Book(@NotNull String name, String description, @NotNull String imagePath, @NotNull String pdfPath,
                @NotNull Language lang, Categorie cat) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.pdfPath = pdfPath;
        this.language = lang;
        this.category = cat;
    }
}