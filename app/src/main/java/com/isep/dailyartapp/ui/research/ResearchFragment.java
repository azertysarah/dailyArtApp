package com.isep.dailyartapp.ui.research;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.isep.dailyartapp.R;
import com.isep.dailyartapp.data.ApolloArtClient;
import com.isep.dailyartapp.databinding.FragmentResearchBinding;
import com.isep.dailyartapp.domain.ArtworkDTO;
import com.isep.dailyartapp.domain.MuseumDTO;
import com.isep.dailyartapp.ui.ArtworkDetailsFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CompletableFuture;

public class ResearchFragment extends Fragment {

    private FragmentResearchBinding binding;

    // For research museum filter
    String [] museumsNameArray = new String[14];
    boolean [] selectedMuseums;
    ArrayList<Integer> museumsIndexList = new ArrayList<>();
    List<String> requestedMuseums = new ArrayList<>();

    // For research movement filter
    String [] movementsNameArray = {"17e siècle", "18e siècle", "19e siècle", "20e siècle"};
    boolean [] selectedMovements;
    ArrayList<Integer> movementsIndexList = new ArrayList<>();
    List<String> requestedMovements = new ArrayList<>();

    // Research parameters
    private String titleParameter = " ";
    private String museumParameter = " ";
    private String timePeriodParameter = " ";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ResearchViewModel researchViewModel = new ViewModelProvider(this).get(ResearchViewModel.class);

        binding = FragmentResearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Default artworks display
        searchArtworks(titleParameter, museumParameter, timePeriodParameter);

        // SELECTION : MUSEUMS
        RelativeLayout museumsSelection = binding.museumsSelection;
        TextView selectMuseumTextView = binding.selectMuseumTextView;
        selectedMuseums = new boolean[museumsNameArray.length];

        museumsSelection.setOnClickListener(view -> {
            displayMuseumsDialog(selectMuseumTextView);
        });

        // Get the museums array
        ApolloArtClient apolloArtClient = new ApolloArtClient();
        CompletableFuture<List<MuseumDTO>> result = apolloArtClient.getMuseumsAsync();
        try {
            List<MuseumDTO> museumDTOS = result.join();
            for(int i = 0; i< museumDTOS.size(); i++) {
                museumsNameArray[i] = museumDTOS.get(i).getName();
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

        // RESEARCH
        SearchView searchView = binding.searchView;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                titleParameter = query;
                searchArtworks(titleParameter, museumParameter, timePeriodParameter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
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

                // Launch research
                museumParameter = stringBuilder.toString();
                searchArtworks(titleParameter, museumParameter, timePeriodParameter);
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

                // Launch research
                museumParameter = " ";
                searchArtworks(titleParameter, museumParameter, timePeriodParameter);
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

                // Launch research
                timePeriodParameter = stringBuilder.toString();
                searchArtworks(titleParameter, museumParameter, timePeriodParameter);
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

                // Launch research
                timePeriodParameter = " ";
                searchArtworks(titleParameter, museumParameter, timePeriodParameter);
            }
        });
        builder.show();
    }
    private void displayArtworkInView(List<ArtworkDTO> artworks) {
        ListView researchListView = binding.researchListView;
        // Display
        String[] artworkStringList = new String[artworks.size()];
        for(int i=0; i<artworkStringList.length; i++) {
            artworkStringList[i] = artworks.get(i).getName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, artworkStringList);
        researchListView.setAdapter(adapter);
        researchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Fetch the clicked artwork data
                ArtworkDTO artwork = artworks.get(position);
                // Pass the data to the details fragment
                ResearchResultFragment researchResultFragment = new ResearchResultFragment();
                Bundle args = new Bundle();

                args.putString("name", artwork.getName());
                //args.putString("artist", artwork.getArtist());
                //args.putString("picture", artwork.getPicture());
                args.putString("timePeriod", artwork.getTimePeriod());
                args.putString("museum", artwork.getMuseum());
                //args.putString("description", artwork.getDescription));

                researchResultFragment.setArguments(args);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment_activity_main, researchResultFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    public void searchArtworks(String title, String museum, String timePeriod) {
        ApolloArtClient apolloArtClient = new ApolloArtClient();
        CompletableFuture<List<ArtworkDTO>> result = apolloArtClient.searchArtworkAsync(title, museum, timePeriod);
        try {
            List<ArtworkDTO> artworks = result.join();
            displayArtworkInView(artworks);
        } catch (Exception e) {
            // Handle exceptions, e.g., if the future completed exceptionally
            Log.e("DAILY_ART", "Error", e);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}