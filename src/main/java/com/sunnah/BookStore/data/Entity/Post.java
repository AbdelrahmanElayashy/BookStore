package com.sunnah.BookStore.data.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "title", nullable = false, columnDefinition = "TEXT")
    @Length(min = 5, message = "*Your title must have at least 5 characters")
    @NotEmpty(message = "*Please provide title")
    private String title;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

/*    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createDate;*/

    @Length(min = 5, message = "Your description must have at least 5 characters")
    @Column(name = "description", columnDefinition = "TEXT")
    @NotEmpty(message="*please provide description")
    private String description;

    @Column(name = "author")
    @NotEmpty(message="*please provide author name")
    private String author;

    public Post(@Length(min = 5, message = "*Your title must have at least 5 characters")
                @NotEmpty(message = "*Please provide title") String title,
                String body,
                @Length(min = 5, message = "Your description must have at least 5 characters")
                @NotEmpty(message = "*please provide description") String description,
                @NotEmpty(message = "*please provide author name") String author) {
        this.title = title;
        this.body = body;
        this.description = description;
        this.author = author;
    }

    /*

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @NotNull
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private Collection<Comment> comments;*/

}