package com.example.a19446101_kt1;

public class Album {
    private String albumId;
    private String albumName;

    public Album(String albumId, String albumName) {
        this.albumId = albumId;
        this.albumName = albumName;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}

