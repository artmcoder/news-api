package com.artmcoder.newsapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
@Data
public class News {
    @Id
    @SequenceGenerator(
            name = "news_id_sequence",
            sequenceName = "news_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "news_id_sequence"
    )
    @Column(name = "id")
    @JsonView(Views.Full.class)
    private Long id;

    @Column(name = "name")
    @Size(max = 70, message = "Field name cannot be lager than 70 symbols")
    @NotBlank(message = "Field name cannot be empty")
    @JsonView(Views.List.class)
    private String name;

    @Column(name = "short_description")
    @Size(max = 255, message = "Field short description cannot be lager than 255 symbols")
    @NotBlank(message = "Field short description cannot be empty")
    @JsonView(Views.List.class)
    private String shortDescription;

    @Column(name = "full_description", columnDefinition = "text")
    @NotBlank(message = "Field full description cannot be empty")
    @JsonView(Views.Full.class)
    private String fullDescription;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "news_type_id")
    @JsonView(Views.List.class)
    private NewsType newsType;

    @Column(name = "creation_time", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.Full.class)
    private LocalDateTime creationTime;

    @PrePersist
    public void onCreate() {
        creationTime = LocalDateTime.now();
    }
}
