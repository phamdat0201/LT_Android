package com.example.a19446101_kt1;

import java.util.ArrayList;
import java.util.List;

public class AlbumManager {
    private static AlbumManager instance;
    private List<Album> albumList;

    private AlbumManager() {
        albumList = new ArrayList<>();
    }

    public static AlbumManager getInstance() {
        if (instance == null) {
            instance = new AlbumManager();
        }
        return instance;
    }

    public void addAlbum(Album album) {
        albumList.add(album);
    }

    public List<Album> getAllAlbums() {
        return albumList;
    }
}
