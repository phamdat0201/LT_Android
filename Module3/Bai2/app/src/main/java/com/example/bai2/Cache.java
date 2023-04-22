package com.example.bai2;

public class Cache {

    private String title;
    private String content;
    private String filePath;

    public Cache(String title, String content, String filePath) {
        this.title = title;
        this.content = content;
        this.filePath = filePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return title;
    }

}
