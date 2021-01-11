package com.sunnah.BookStore.data.Entity;

import com.sunnah.BookStore.business.domain.Categorie;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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

    @NotNull
    @Enumerated(EnumType.STRING)
    private Categorie category;


    public Book(@NotNull String name, String description, @NotNull String imagePath, @NotNull String pdfPath, @NotNull Categorie category) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.pdfPath = pdfPath;
        this.category = category;
    }
}