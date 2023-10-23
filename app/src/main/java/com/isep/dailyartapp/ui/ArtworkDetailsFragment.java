package com.isep.dailyartapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.isep.dailyartapp.databinding.ArtworkDetailsBinding;

public class ArtworkDetailsFragment extends Fragment {

    private ArtworkDetailsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // ResearchViewModel artworkDetailViewModel = new ViewModelProvider(this).get(ResearchViewModel.class);

        binding = ArtworkDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
}
