package com.mrproducts.www.trending_repo;

public class Repository {
    private String title,link, description;

    Repository(String title, String link, String description){
        this.title = title;
        this.link = link;
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }
}
