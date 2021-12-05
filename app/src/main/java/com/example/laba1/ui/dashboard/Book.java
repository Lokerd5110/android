package com.example.laba1.ui.dashboard;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("Author")
    @Expose
    private String author;

    @SerializedName("Genre")
    @Expose
    private String genre;

    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("PublicationDate")
    @Expose
    private String publicationDate;

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

}