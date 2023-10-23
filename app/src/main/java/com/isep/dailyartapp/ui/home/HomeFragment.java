package com.isep.dailyartapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.isep.dailyartapp.R;
import com.isep.dailyartapp.databinding.FragmentHomeBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private TextView dateTextView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Récupérez la référence du TextView à partir de votre mise en page XML.
        dateTextView = view.findViewById(R.id.dateTextView);

        // Date du jour
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String date = dateFormat.format(calendar.getTime());

        // Affichez la date dans le TextView.
        dateTextView.setText(date);

        return view;


        // Ajoutez des gestionnaires d'événements pour les interactions ici
        /*interactiveIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lors du clic sur l'icône interactive
            }
        });*/
    }

    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }*/
}
