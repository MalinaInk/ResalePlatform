package com.malina_ink.resaleplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ad")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "price")
    private int price;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "ad_image")
    private String adImage;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.DETACH)
    private List<Comment> comments = new ArrayList<>();
}
