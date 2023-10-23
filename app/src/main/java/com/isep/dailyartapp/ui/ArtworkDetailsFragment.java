package com.isep.dailyartapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.isep.dailyartapp.databinding.ArtworkDetailsBinding;

public class ArtworkDetailsFragment extends Fragment {

    private ArtworkDetailsBinding binding;
    private TextView titleData;

    private String nameData;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // ResearchViewModel artworkDetailViewModel = new ViewModelProvider(this).get(ResearchViewModel.class);

        binding = ArtworkDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Log.d("DAILY_ART", "blabla");
        //titleData = binding.titleData;
        //titleData.setText(nameData);
        //Log.d("DAILY_ART", String.valueOf(titleData.getText()));

        return root;
    }

    public void receiveData(String name, String picture, String artist, String timePeriod, String museum, String description) {
        Log.d("DAILY_ART", name);
        nameData = name;
        //titleData.setText(name);
        /*
        //TextView artistData = binding.artistData;
        //artistData.setText(artist);

        TextView timePeriodData = binding.timePeriodData;
        timePeriodData.setText(timePeriod);

        TextView museumData = binding.museumData;
        museumData.setText(museum);

        //TextView descriptionData = binding.descriptionData;
        //descriptionData.setText(description);*/
    }
}
