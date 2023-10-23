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
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private TextView dateTextView;
    private TextView titleData;
    private TextView artistData;
    private TextView timePeriodData;
    private TextView museumData;
    private TextView descriptionData;
    private ImageView pictureData;

    private void retrieveArtworkDetails() {
        // Vous pouvez utiliser une bibliothèque de réseau, des appels d'API, ou des données enregistrées localement ici pour récupérer les détails de l'œuvre.

        // Exemple fictif de récupération de données depuis une source de données :
        String artworkTitle = "Soleil couchant sur la Seine à Lavacourt, effet d'hiver";
        String artistName = "Monet, Claude";
        String museumName = "Petit Palais, musée des Beaux-arts de la Ville de Paris";
        String period = "En 1880";
        String description = "Vue panoramique du village de Lavacourt dans les Yvelines depuis l'autre berge de la Seine. Placé au centre de la composition, le soleil aux couleurs intenses se reflète sur l'eau et contraste avec l'atmosphère générale bleue.";
        String imageUrl = "https://parismuseescollections.paris.fr/sites/default/files/atoms/images/PPA/lpdp_143990-30.jpg";

        // Une fois les données récupérées, mise à jour les vues appropriées.

        titleData.setText(artworkTitle);
        artistData.setText(artistName);
        timePeriodData.setText(period);
        museumData.setText(museumName);
        descriptionData.setText(description);

        // Chargez l'image de l'œuvre à partir de l'URL (utilisez une bibliothèque de chargement d'image comme Picasso ou Glide).
        // Exemple fictif :
        Picasso.get().load(imageUrl).into(pictureData);
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Récupérez la référence du TextView à partir de votre mise en page XML.
        dateTextView = view.findViewById(R.id.dateTextView);
        titleData = view.findViewById(R.id.titleData);
        artistData = view.findViewById(R.id.artistData);
        timePeriodData = view.findViewById(R.id.timePeriodData);
        museumData = view.findViewById(R.id.museumData);
        descriptionData = view.findViewById(R.id.descriptionData);
        pictureData = view.findViewById(R.id.pictureData);


        // Date du jour
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String date = dateFormat.format(calendar.getTime());

        // Affichez la date dans le TextView.
        dateTextView.setText(date);

        // Affichage Data lié à une oeuvre.
        retrieveArtworkDetails();

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
