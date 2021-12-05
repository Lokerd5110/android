package com.example.laba1.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.laba1.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private static String[] puts;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        puts = getArguments().getStringArray("MyArg");

        TextView this1 = binding.homeTextView;
        TextView this2 = binding.homeTextView2;
        TextView this3 = binding.homeTextView3;
        TextView this4 = binding.homeTextView4;

        if (puts != null) {
            this1.setText("Автор: " + puts[0]);
            this2.setText("Жанр: " + puts[1]);
            this3.setText(puts[2]);
            this4.setText("Год публикации: " + puts[3]);
        }

        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                ;
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}