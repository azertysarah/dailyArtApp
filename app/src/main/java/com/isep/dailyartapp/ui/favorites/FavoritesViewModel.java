package com.isep.dailyartapp.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class FavoritesViewModel extends ViewModel {

    private final MutableLiveData<List<Artwork>> mArtworkList;

    public FavoritesViewModel() {
        mArtworkList = new MutableLiveData<>();
        // Remplissez la liste d'œuvres avec les œuvres favorites
        // Vous pouvez obtenir les œuvres favorites à partir de votre source de données
        // Par exemple, mArtworkList.setValue(getFavoriteArtworks());
    }

    public LiveData<List<Artwork>> getArtworkList() {
        return mArtworkList;
    }
}
