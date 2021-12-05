package com.example.laba1.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.laba1.R;
import com.example.laba1.databinding.FragmentHomeBinding;
import com.example.laba1.ui.MyRecyclerViewAdapter;
import com.example.laba1.ui.dashboard.Book;
import com.example.laba1.ui.dashboard.Purs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private SwipeRefreshLayout mySwipeRefreshLayout;
    private FragmentHomeBinding binding;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> author = new ArrayList<>();
    private List<String> genre = new ArrayList<>();
    private List<String> name = new ArrayList<>();
    private List<String> publ = new ArrayList<>();
    private boolean flag = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


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
                    puts.sort(((o1, o2) -> (-1) * o2.getName().compareTo(o1.getName())));

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

                    mySwipeRefreshLayout.setOnRefreshListener(() -> {

                        if (!flag) {
                            puts.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
                        } else {
                            puts.sort((o1, o2) -> (-1) * o2.getName().compareTo(o1.getName()));
                        }
                        flag = !flag;

                        author.clear();
                        genre.clear();
                        name.clear();
                        publ.clear();

                        for(int i =0;i<puts.size();i++) {
                            author.add(puts.get(i).getAuthor());
                            genre.add(puts.get(i).getGenre());
                            name.add(puts.get(i).getName());
                            publ.add(puts.get(i).getPublicationDate());
                        }

                        mAdapter.notifyDataSetChanged();
                        mySwipeRefreshLayout.setRefreshing(false);
                    });
                }
                else {
                    Log.i("Response", "No response");
                }
            }
            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
            }
        });


        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                ;
            }
        });

        mRecyclerView = root.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mySwipeRefreshLayout = root.findViewById(R.id.mySwipeRefreshLayout);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}