package com.example.marvel_workshop39.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Comments implements Serializable {
    private String hero_id;
    private String heroName;
    private String comments;
    public String getHero_id() {
        return hero_id;
    }
    public void setHero_id(String hero_id) {
        this.hero_id = hero_id;
    }
    public String getHeroName() {
        return heroName;
    }
    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    @Override
    public String toString() {
        return "Comments [hero_id=" + hero_id + ", heroName=" + heroName + ", comments=" + comments + "]";
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("hero_id", this.getHero_id())
            .add("heroName", this.getHeroName())
            .add("comments", this.getComments())
            .build();
    }
    
    
    
    
}
