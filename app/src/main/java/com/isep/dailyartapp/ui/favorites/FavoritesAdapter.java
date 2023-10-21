package com.isep.dailyartapp.ui.favorites;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.isep.dailyartapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/*public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private List<Artwork> artworkList;

    public void setArtworkList(List<Artwork> artworkList) {
        this.artworkList = artworkList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Artwork artwork = artworkList.get(position);
        // Mettez à jour les vues du ViewHolder avec les détails de l'œuvre (image, titre, etc.)
    }

    @Override
    public int getItemCount() {
        return artworkList != null ? artworkList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Déclarez vos vues pour l'image, le titre, etc., ici
        public ViewHolder(View itemView) {
            super(itemView);
            // Initialisez les vues ici
        }
    }
}*/

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {
    private List<Artwork> favoritesList;

    public FavoritesAdapter(List<Artwork> favoritesList) {
        this.favoritesList = favoritesList;
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        Artwork artwork = favoritesList.get(position);

        // Afficher le titre de l'œuvre
        holder.titleTextView.setText(artwork.getTitle());

        // Utilisez une bibliothèque comme Picasso ou Glide pour charger l'image depuis l'URL
        Picasso.get().load(artwork.getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return favoritesList.size();
    }

    public static class FavoritesViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;

        public FavoritesViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
        }
    }
}
