package com.isep.dailyartapp.ui.research;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.isep.dailyartapp.databinding.FragmentResearchBinding;

public class ResearchFragment extends Fragment {

    private FragmentResearchBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ResearchViewModel researchViewModel =
                new ViewModelProvider(this).get(ResearchViewModel.class);

        binding = FragmentResearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textResearch;
        researchViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}