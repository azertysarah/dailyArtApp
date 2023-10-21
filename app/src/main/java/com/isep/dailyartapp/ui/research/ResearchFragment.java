package com.isep.dailyartapp.ui.research;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.isep.dailyartapp.data.ApolloMuseumClient;
import com.isep.dailyartapp.databinding.FragmentResearchBinding;
import com.isep.dailyartapp.domain.Museum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ResearchFragment extends Fragment {

    private FragmentResearchBinding binding;

    // For research museum filter
    String [] museumsNameArray = new String[14];
    boolean [] selectedMuseums;
    ArrayList<Integer> museumsIndexList = new ArrayList<>();

    // For research movement filter
    String [] movementsNameArray = {"Baroque", "Classicisme", "Romantisme", "Réalisme"};
    boolean [] selectedMovements;
    ArrayList<Integer> movementsIndexList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ResearchViewModel researchViewModel = new ViewModelProvider(this).get(ResearchViewModel.class);

        binding = FragmentResearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textResearch;
        researchViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // RESEARCH
        SearchView searchView = binding.searchView;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // SELECTION : MUSEUMS
        RelativeLayout museumsSelection = binding.museumsSelection;
        TextView selectMuseumTextView = binding.selectMuseumTextView;
        selectedMuseums = new boolean[museumsNameArray.length];

        museumsSelection.setOnClickListener(view -> {
            displayMuseumsDialog(selectMuseumTextView);
        });

        // Get the museums array
        ApolloMuseumClient apolloMuseumClient = new ApolloMuseumClient();
        CompletableFuture<List<Museum>> result = apolloMuseumClient.doSomethingAsync();
        try {
            List<Museum> museums = result.join();
            for(int i=0; i<museums.size(); i++) {
                museumsNameArray[i] = museums.get(i).getName();
            }
        } catch (Exception e) {
            Log.e("DAILY_ART", "Error: ", e);
        }

        // SELECTION : MOVEMENTS
        RelativeLayout movementsSelection = binding.movementsSelection;
        TextView selectMovementTextView = binding.selectMovementTextView;
        selectedMovements = new boolean[museumsNameArray.length];
        movementsSelection.setOnClickListener(view -> {
            displayMovementsDialog(selectMovementTextView);
        });

        return root;
    }

    private void displayMuseumsDialog(TextView selectMuseumTextView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Sélectionnez des musées :");
        builder.setCancelable(false);
        builder.setMultiChoiceItems(museumsNameArray, selectedMuseums, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int museumIndex, boolean isChecked) {
                if(isChecked) {
                    museumsIndexList.add(museumIndex);
                } else {
                    museumsIndexList.remove(museumIndex);
                }
            }
        }).setPositiveButton("Valider", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int museumIndex) {
                StringBuilder stringBuilder = new StringBuilder();
                for(int i = 0; i< museumsIndexList.size(); i++) {
                    stringBuilder.append(museumsNameArray[museumsIndexList.get(i)]);
                    if( i != museumsIndexList.size()-1 ) {
                        stringBuilder.append(",");
                    }
                }
                dialogInterface.dismiss();
                selectMuseumTextView.setText(stringBuilder.toString());
            }
        }).setNeutralButton("Effacer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int museumIndex) {
                for(int i=0; i<selectedMuseums.length; i++) {
                    selectedMuseums[i] = false;
                    museumsIndexList.clear();
                    selectMuseumTextView.setText("");
                }
                dialogInterface.dismiss();
            }
        });
        builder.show();
}

    private void displayMovementsDialog(TextView selectMovementsTextView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Sélectionnez des mouvements :");
        builder.setCancelable(false);
        builder.setMultiChoiceItems(movementsNameArray, selectedMovements, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int movementIndex, boolean isChecked) {
                if(isChecked) {
                    movementsIndexList.add(movementIndex);
                } else {
                    movementsIndexList.remove(movementIndex);
                }
            }
        }).setPositiveButton("Valider", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int movementIndex) {
                StringBuilder stringBuilder = new StringBuilder();
                for(int i = 0; i< movementsIndexList.size(); i++) {
                    stringBuilder.append(movementsNameArray[movementsIndexList.get(i)]);
                    if( i != movementsIndexList.size()-1 ) {
                        stringBuilder.append(",");
                    }
                }
                dialogInterface.dismiss();
                selectMovementsTextView.setText(stringBuilder.toString());
            }
        }).setNeutralButton("Effacer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int movementIndex) {
                for(int i=0; i<selectedMovements.length; i++) {
                    selectedMovements[i] = false;
                    movementsIndexList.clear();
                    selectMovementsTextView.setText("");
                }
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    public void search(String title) {
        ApolloMuseumClient apolloMuseumClient = new ApolloMuseumClient();
        CompletableFuture<List<Museum>> result = apolloMuseumClient.doSomethingAsync();
        try {
            List<Museum> museums = result.join(); // Wait for the future to complete and get the result
            Log.d("SARAH", museums.toString());

            Log.d("SARAH", museums.get(0).getName().toString());
        } catch (Exception e) {
            // Handle exceptions, e.g., if the future completed exceptionally
            Log.e("SARAH", "Error", e);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}