package com.artmcoder.newsapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "news_types")
@Data
public class NewsType {
    @Id
    @SequenceGenerator(
            name = "news_type_id_sequence",
            sequenceName = "news_type_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "news_type_id_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Field name cannot be empty")
    @Size(max = 40, message = "Field name cannot be lager than 40 symbols")
    @JsonView(Views.List.class)
    private String name;

    @Column(name = "color")
    @NotBlank(message = "Field color cannot be empty")
    @Size(max = 20, message = "Field color cannot be lager than 20 symbols")
    @JsonView(Views.List.class)
    private String color;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "newsType")
    private List<News> newsList = new ArrayList<>();

    @Column(name = "creation_time", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationTime;

    @PrePersist
    public void onCreate() {
        creationTime = LocalDateTime.now();
    }
}
