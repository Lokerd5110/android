package com.example.wear;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.wear.widget.WearableLinearLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends Activity {


    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> author = new ArrayList<>();
    private List<String> genre = new ArrayList<>();
    private List<String> name = new ArrayList<>();
    private List<String> publ = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Purs purs = retrofit.create(Purs.class);

        Call<List<Book>> call = purs.getBooks();

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(response.isSuccessful()) {

                    List<Book> puts = response.body();

                    author.clear();
                    genre.clear();
                    name.clear();
                    publ.clear();

                    for (int i = 0; i<puts.size(); i++) {
                        author.add(puts.get(i).getAuthor());
                        genre.add(puts.get(i).getGenre());
                        name.add(puts.get(i).getName());
                        publ.add(puts.get(i).getPublicationDate());

                        Log.i("Book", "Автор: " + puts.get(i).getAuthor());
                        Log.i("Book", "Жанр: " + puts.get(i).getGenre());
                        Log.i("Book", "Имя: " + puts.get(i).getName());
                        Log.i("Book", "Дата публикации: " + puts.get(i).getPublicationDate());
                    }

                    MyRecyclerViewAdapter mAdapter = new MyRecyclerViewAdapter(author, genre, name, publ);
                    mRecyclerView.setAdapter(mAdapter);

                }
                else {
                    Log.i("Response", "No response");
                }
            }
            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
            }
        });

        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new WearableLinearLayoutManager(ListActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mRecyclerView.setCircularScrollingGestureEnabled(true);
        //mRecyclerView.setEdgeItemsCenteringEnabled(true);

    }
}