package com.malina_ink.resaleplatform.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class CommentDto {
    private int author;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private int pk;
    private String text;

    public CommentDto(int author,
                      String authorImage,
                      String authorFirstName,
                      Long createdAt,
                      int pk,
                      String text) {
        this.author = author;
        this.authorImage = authorImage;
        this.authorFirstName = authorFirstName;
        this.createdAt = createdAt;
        this.pk = pk;
        this.text = text;
    }

    public CommentDto(int author,
                      String authorFirstName,
                      Long createdAt,
                      int pk,
                      String text) {
        this.author = author;
        this.authorFirstName = authorFirstName;
        this.createdAt = createdAt;
        this.pk = pk;
        this.text = text;
    }

    public CommentDto() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDto that = (CommentDto) o;
        return author == that.author && pk == that.pk && Objects.equals(authorImage, that.authorImage) && authorFirstName.equals(that.authorFirstName) && createdAt.equals(that.createdAt) && text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, authorImage, authorFirstName, createdAt, pk, text);
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "author=" + author +
                ", authorImage='" + authorImage + '\'' +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", createdAt=" + createdAt +
                ", pk=" + pk +
                ", text='" + text + '\'' +
                '}';
    }
}
