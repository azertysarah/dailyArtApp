package com.isep.dailyartapp.ui.favorites;

/*public class Artwork {
    private String title;
    private String artist;
    private String description;
    private int imageResId;  // L'ID de ressource de l'image (cette valeur peut être un URL si vous chargez des images à partir d'Internet)

    public Artwork(String title, String artist, String description, int imageResId) {
        this.title = title;
        this.artist = artist;
        this.description = description;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}*/
public class Artwork {
    private String title;
    private String imageUrl;

    public Artwork(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}


