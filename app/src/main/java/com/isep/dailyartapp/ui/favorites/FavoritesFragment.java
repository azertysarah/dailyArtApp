package com.isep.dailyartapp.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.isep.dailyartapp.R;
import com.isep.dailyartapp.databinding.FragmentFavoritesBinding;

import java.util.ArrayList;
import java.util.List;

/*public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavoritesViewModel favoritesViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textFavorites;
        favoritesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}*/
/*public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;
    private RecyclerView recyclerView;
    private FavoritesAdapter favoritesAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavoritesViewModel favoritesViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.recyclerViewFavorites;
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2)); // Affichage côte à côte

        favoritesAdapter = new FavoritesAdapter(); // Créez un adaptateur approprié
        recyclerView.setAdapter(favoritesAdapter);

        favoritesViewModel.getArtworkList().observe(getViewLifecycleOwner(), artworkList -> {
            // Mettez à jour les œuvres dans l'adaptateur
            favoritesAdapter.setArtworkList(artworkList);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}*/
public class FavoritesFragment extends Fragment {
    private RecyclerView recyclerView;
    private FavoritesAdapter favoritesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerView = root.findViewById(R.id.recyclerViewFavorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Artwork> favoritesList = createFavoritesList(); // Remplacez ceci par la liste réelle des œuvres favorites
        favoritesAdapter = new FavoritesAdapter(favoritesList);
        recyclerView.setAdapter(favoritesAdapter);

        return root;
    }

    // Remplacez ceci par la méthode pour récupérer la liste réelle des œuvres favorites
    private List<Artwork> createFavoritesList() {
        List<Artwork> favoritesList = new ArrayList<>();
        favoritesList.add(new Artwork("Titre de l'Œuvre 1", "URL de l'image 1"));
        favoritesList.add(new Artwork("Titre de l'Œuvre 2", "URL de l'image 2"));
        return favoritesList;
    }
}

