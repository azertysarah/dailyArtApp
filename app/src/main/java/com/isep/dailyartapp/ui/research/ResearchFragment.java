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

import com.isep.dailyartapp.databinding.FragmentResearchBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class ResearchFragment extends Fragment {

    private FragmentResearchBinding binding;

    // For research museum filter
    String [] museumsNameArray = {"Louvres", "Maison de Blazac", "Musée d'Orsay", "Musée du Quai Branly"};
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
        Log.d("SARAH", "Museums dialog");
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

    private void search(String text) {
        Log.d("SARAH", text);

        // HttpClient client = HttpClient.newHttpClient();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}