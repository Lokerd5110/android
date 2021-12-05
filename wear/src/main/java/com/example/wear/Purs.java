package com.example.wear;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Purs {

    @GET("Lpirskaya/JsonLab/master/Books1.json")
    Call<List<Book>> getBooks();
}
