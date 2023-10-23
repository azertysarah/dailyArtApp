package com.isep.dailyartapp.ui.research;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.isep.dailyartapp.R;
import com.isep.dailyartapp.databinding.ArtworkDetailsBinding;
import com.isep.dailyartapp.databinding.FragmentResearchResultBinding;
import com.isep.dailyartapp.ui.ArtworkDetailsFragment;

public class ResearchResultFragment extends Fragment {
    private FragmentResearchResultBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // ResearchViewModel artworkDetailViewModel = new ViewModelProvider(this).get(ResearchViewModel.class);

        binding = FragmentResearchResultBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Get the artwork details data
        Bundle args = getArguments();
        if(args != null) {
            String name = args.getString("name");
            String artist = args.getString("artist");
            String picture = args.getString("picture");
            String timePeriod = args.getString("timePeriod");
            String museum = args.getString("museum");
            String description = args.getString("description");

            /*// Pass the details data to the details view (artwork_details.xml)
            ArtworkDetailsFragment artworkDetailsFragment = new ArtworkDetailsFragment();
            Bundle args2 = new Bundle();

            args2.putString("name", name);
            //args2.putString("artist", artist);
            //args2.putString("picture", picture);
            args2.putString("timePeriod", timePeriod);
            args2.putString("museum", museum);
            //args2.putString("description", description);

            artworkDetailsFragment.setArguments(args2);*/
            ArtworkDetailsFragment artworkDetailsFragment = new ArtworkDetailsFragment();
                    //(ArtworkDetailsFragment) getChildFragmentManager().findFragmentById(R.id.artworkDetails);
            Log.d("DAILY_ART", "okok");
            artworkDetailsFragment.receiveData(name, picture, artist, timePeriod, museum, description);
        }

        // Close the fragment after a click on "Retourner à la recherche"
        LinearLayout backToResearchLinearLayout = binding.linearLayout;
        backToResearchLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager != null) {
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.remove(ResearchResultFragment.this);
                    transaction.commit();
                }
            }
        });

        return root;
    }
}
