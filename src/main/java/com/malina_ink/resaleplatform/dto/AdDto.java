package com.malina_ink.resaleplatform.dto;


import lombok.Data;

import java.util.Objects;

@Data
public class AdDto {
    private int author;
    private String image;
    private int pk;
    private int price;
    private String title;

    public AdDto(int author,
                 String image,
                 int pk,
                 int price,
                 String title) {
        this.author = author;
        this.image = image;
        this.pk = pk;
        this.price = price;
        this.title = title;
    }

    public AdDto(int author,
                 int pk,
                 int price,
                 String title) {
        this.author = author;
        this.pk = pk;
        this.price = price;
        this.title = title;
    }

    public AdDto() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdDto adDto = (AdDto) o;
        return author == adDto.author && pk == adDto.pk && price == adDto.price && Objects.equals(image, adDto.image) && title.equals(adDto.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, image, pk, price, title);
    }

    @Override
    public String toString() {
        return "AdDto{" +
                "author=" + author +
                ", image='" + image + '\'' +
                ", pk=" + pk +
                ", price=" + price +
                ", title='" + title + '\'' +
                '}';
    }
}
