package com.example.a25492.myapplication;

public class MusicMedia {
    private int id;
    private String title;
    private String artist;
    private String url;
    private int time;
    private Long size;
    private String albumId;
    private String album;
    public void setId(int id) {
        this.id = id;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }
}
